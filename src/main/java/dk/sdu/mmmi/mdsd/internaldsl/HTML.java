package dk.sdu.mmmi.mdsd.internaldsl;

import dk.sdu.mmmi.mdsd.internaldsl.metamodel.HTMLComponent;
import dk.sdu.mmmi.mdsd.internaldsl.metamodel.HTMLConfigComponent;
import dk.sdu.mmmi.mdsd.internaldsl.metamodel.HTMLTagComponent;
import dk.sdu.mmmi.mdsd.internaldsl.metamodel.HTMLRoot;
import dk.sdu.mmmi.mdsd.internaldsl.metamodel.HTMLTag;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The HTML class allows easy description of an HTML metamodel, 
 * with both CSS styling, tag properties, and 
 * subsequent code generation
 * @author Victor Gram Thomsen, vitho17
 */
public class HTML {
    
    /**
     * Builder class, providing methods for building an HTML metamodel
     */
    public static abstract class HTMLBuilder {
        
        /**
         * The root HTML tag
         */
        private HTMLRoot top;
        
        /**
         * Create and HTMLRoot instance for all subsequent HTMLComponents to be nested within.
         * Accepts HTMLConfigComponents of type CSS.
         * @param components
         * @return HTMLBuilder instance
         */
        public HTMLBuilder HTMLRoot(HTMLComponent ... components) { 
            top = new HTMLRoot(components);
            return this;
        }
        
        
        /**
         * Create an HTMLTag, nesting any HTMLTags and HTMLConfigComponents related to the tag
         * @param tagType
         * @param tagValue
         * @param nestedComponents 0..* HTMLComponents to be nested within or describing the tag being created
         * @return the created tag
         */
        public HTMLTag tag(String tagType, String tagValue, HTMLComponent ... nestedComponents) { 
            HTMLTag tag = new HTMLTag(tagType, tagValue);
            for(HTMLComponent component : nestedComponents) { 
                if(component instanceof HTMLTag) {
                    tag.addNestedTag((HTMLTag) component);
                }
                
                if(component instanceof HTMLConfigComponent) { 
                    if(((HTMLConfigComponent) component).getConfigType() == HTMLConfigComponent.TYPE.CSS) {
                        tag.getStylingList().add((HTMLConfigComponent) component);
                    } 
                    
                    if(((HTMLConfigComponent) component).getConfigType() == HTMLConfigComponent.TYPE.PROPERTY) {
                        tag.addProperty((HTMLConfigComponent) component);
                    } 
                }
                
            }
            return tag;
        }         
        
        /**
         * Create an HTMLConfigComponent with TYPE CSS
         * @param cssProperty
         * @param cssValue
         * @return the HTMLConfigComponent
         */
        public HTMLConfigComponent styling(String cssProperty, String cssValue) { 
            HTMLConfigComponent stylingConfig = new HTMLConfigComponent(cssProperty, cssValue, HTMLConfigComponent.TYPE.CSS);
            return stylingConfig;
        }
        
        /**
         * Create an HTMLConfigComponent with TYPE PROPERTY
         * @param propertyName 
         * @param propertyValue 
         * @return the HTMLConfigComponent
         */
        public HTMLConfigComponent property(String propertyName, String propertyValue) { 
            HTMLConfigComponent propertyConfig = new HTMLConfigComponent(propertyName, propertyValue, HTMLConfigComponent.TYPE.PROPERTY);
            return propertyConfig;
        }
        
        
        public abstract void build();
        
        /**
         * Get the HTMLRoot
         * @return the HTMLRoot
         */
        public HTMLRoot getTop() { 
            build();
            return top;
        }
    }  
    
    /**
     * Build the model
     * @param model 
     */
    public HTML(final HTMLBuilder model) { 
        generateHTML(model.getTop());
    }
    
    /**
     * Begin construction of HTML code, starting with the root tag, moving through the nested tags.
     * Passes a StringBuilder around, which will end up containing the complete HTML string.
     * Finishes by passing StringBuilder to method which will create the .html file.
     * @param top the HTMLRoot
     */
    private void generateHTML(HTMLRoot top) {
        StringBuilder htmlStringBuilder = new StringBuilder();
        htmlStringBuilder.append("<html ");
        
        if(!top.getStylingList().isEmpty()) { 
            printCSS(top, htmlStringBuilder);
        }
         
        htmlStringBuilder.append(">").append("\n");
        
        for(HTMLTagComponent tag : top.getNestedTags()) { 
            printTag((HTMLTag)tag, htmlStringBuilder);
        }
        htmlStringBuilder.append("</html>");
        System.out.println(htmlStringBuilder.toString());
        
        printToFile(htmlStringBuilder);
    }
    
    /**
     * Appends a specific tag to the StringBuilder, along with it's styling
     * @param tag
     * @param htmlStringBuilder 
     */
    private void printTag(HTMLTag tag, StringBuilder htmlStringBuilder) { 
        htmlStringBuilder.append("<").append(tag.getTagType());
        
        if(!tag.getPropertyList().isEmpty()) { 
            printProperties(tag, htmlStringBuilder);
        }
        
        if(!tag.getStylingList().isEmpty()) { 
            printCSS(tag, htmlStringBuilder);
        }
                
        htmlStringBuilder.append(">").append("\n");
        if(!tag.getTagValue().equals("")) {htmlStringBuilder.append(tag.getTagValue()).append("\n");}
        for(HTMLTagComponent nextTag : tag.getNestedTags()) { 
            printTag((HTMLTag) nextTag, htmlStringBuilder);
        }
        htmlStringBuilder.append("</").append(tag.getTagType()).append(">").append("\n");
    }
    
    /**
     * Appends a StringBuilder containing the style property to the exisiting HTML StringBuilder.
     * @param tag current tag being written
     * @param HTMLStringBuilder the stringbuilder containing the HTML output
     */
    private void printCSS(HTMLTagComponent tag,StringBuilder HTMLStringBuilder) {
        StringBuilder stylingStringBuilder = new StringBuilder();
        stylingStringBuilder.append(" ").append("style=").append("\"");
            
        for(HTMLConfigComponent cssPair : tag.getStylingList()) { 
            stylingStringBuilder.append(cssPair.getKey()).append(":")
                    .append(cssPair.getValue()).append(";").append(" ");
        }

        stylingStringBuilder.append("\"").append(" ");
        HTMLStringBuilder.append(stylingStringBuilder);
    }
    
    /**
     * Appends a StringBuilder containing properties related to the current tag to the exisiting HTML StringBuilder.
     * @param tag current tag being written
     * @param HTMLStringBuilder the stringbuilder containing the HTML output
     */
    private void printProperties(HTMLTag tag,StringBuilder HTMLStringBuilder) {
        StringBuilder propertyStringBuilder = new StringBuilder();
        propertyStringBuilder.append(" ");
            
        for(HTMLConfigComponent propertyPair : tag.getPropertyList()) { 
            propertyStringBuilder
                    .append(propertyPair.getKey())
                    .append("=")
                    .append("\"")
                    .append(propertyPair.getValue())
                    .append("\"")
                    .append(" ");
        }

        propertyStringBuilder.append(" ");
        HTMLStringBuilder.append(propertyStringBuilder);
    }
    
    /**
     * Prints the HTML to a file in the project root.
     * Overwrites any files with the same name in the project root.
     * @param htmlStringBuilder 
     */
    private void printToFile(StringBuilder htmlStringBuilder) { 
        File file = new File("index.html");
        
        try {
            if(!file.createNewFile()) { 
                file.delete();
            }
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            writer.append(htmlStringBuilder);
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(HTML.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
