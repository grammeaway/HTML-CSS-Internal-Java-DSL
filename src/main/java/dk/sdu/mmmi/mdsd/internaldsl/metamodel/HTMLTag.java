package dk.sdu.mmmi.mdsd.internaldsl.metamodel;

import java.util.ArrayList;
import java.util.List;

/**
 * Metamodel: Any given HTML tag which is not the root 
 * 
 * @author Victor Gram Thomsen, vitho17
 */
public class HTMLTag extends HTMLTagComponent {
    
    /**
     * Tag type, e.g. "h1", "div" or "button"
     */
    private String tagType;
    /**
     * Tag value, e.g. the text for an "h1" tag
     */
    private String tagValue;
    /**
     * HTMLConfig Properties of the HTML tag
     */
    private List<HTMLConfigComponent> propertyList;
    
    public HTMLTag(String tagType, String tagValue) { 
        this.tagType = tagType;
        this.tagValue = tagValue;
        this.propertyList = new ArrayList<>();
    }
    
    /**
     * Add an HTMLConfig property to the specified tag
     * @param property
     */
    public void addProperty(HTMLConfigComponent property) { 
        propertyList.add(property);
    }

    /**
     * Add multiple HTMLConfig properties as a list 
     * @param propertyList 
     */
    public void addProperties(List<HTMLConfigComponent> propertyList) { 
        this.propertyList.addAll(propertyList);
    }

    /**
     * Returns the type of the HTML tag, e.g. "h1", "div" or "button"
     * @return tagType
     */
    public String getTagType() {
        return tagType;
    }

    /**
     * Returns the value assigned to the HTML tag, e.g. text written within an h1 tag
     * @return tagValue
     */
    public String getTagValue() {
        return tagValue;
    }

    /**
     * Getter for the list of HTMLConfigComponents of type PROPERTY associated with the tag
     * @return 
     */
    public List<HTMLConfigComponent> getPropertyList() {
        return propertyList;
    }      
}
