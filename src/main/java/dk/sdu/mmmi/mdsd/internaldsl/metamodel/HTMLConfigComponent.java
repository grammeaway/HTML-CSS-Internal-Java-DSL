package dk.sdu.mmmi.mdsd.internaldsl.metamodel;

/**
 * Metamodel: A configuration belonging to an HTMLTagComponent, 
 * either in the form of styling or a property 
 * @author Victor Gram Thomsen, vitho17
 */
public class HTMLConfigComponent extends HTMLComponent {
    
    /**
     * Enum describing whether the config is a 
     * CSS type or a property type
     */
    public enum TYPE { CSS, PROPERTY;}
    
    /**
     * The key in the config key-value pair
     */
    private String key;
    
    /**
     * The key in the config key-value pair
     */
    private String value;
    
    /**
     * Type of the config component
     */
    private HTMLConfigComponent.TYPE configType;
    
    public HTMLConfigComponent(String key, String value, HTMLConfigComponent.TYPE type) { 
        this.key = key;
        this.value = value;
        this.configType = type;
    }

    /**
     * Getter for the config key
     * @return key
     */
    public String getKey() {
        return key;
    }

    /**
     * Getter for the config value
     * @return value
     */
    public String getValue() {
        return value;
    }

    /**
     * Getter for the configuration type
     * @return configType
     */
    public TYPE getConfigType() {
        return configType;
    }
}
