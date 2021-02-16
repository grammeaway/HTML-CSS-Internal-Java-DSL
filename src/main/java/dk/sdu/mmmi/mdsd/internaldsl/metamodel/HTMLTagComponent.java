package dk.sdu.mmmi.mdsd.internaldsl.metamodel;

import java.util.ArrayList;
import java.util.List;

/**
 * Metamodel: Abstract superclass describing any HTML tag in the model
 * 
 * @author Victor Gram Thomsen, vitho17
 */
public abstract class HTMLTagComponent extends HTMLComponent {
    
    /**
     * List of HTMLConfigComponents of type CSS
     */
    protected List<HTMLConfigComponent> stylingList = new ArrayList<>();
    /**
     * List of all tags nested within the HTML component
     */
    protected List<HTMLTagComponent> nestedTagList = new ArrayList<>();
    
    /**
     * Add a single styling key-value pair to the component,
     * in the form of an HTMLConfigComponent of type CSS
     * @param stylingConfig
     */
    public void addStyling(HTMLConfigComponent stylingConfig) { 
        stylingList.add(stylingConfig);
    }
    
    /**
     * Add a list of HTMLConfigComponent of type CSS instances to the tag
     * @param stylingList 
     */
    public void addStyling(List<HTMLConfigComponent> stylingList) { 
        this.stylingList.addAll(stylingList);
    }
     
    /**
     * Add a tag to be nested within the component
     * @param tag 
     */
    public void addNestedTag(HTMLTagComponent tag) { 
        nestedTagList.add(tag);
    }

    /**
     * Get list of tags nested within the component
     * @return 
     */
    public List<HTMLTagComponent> getNestedTags() {
        return nestedTagList;
    }

    /**
     * Get list containing the component's styling HTLMConfigComponents 
     * @return 
     */
    public List<HTMLConfigComponent> getStylingList() {
        return stylingList;
    }
}
