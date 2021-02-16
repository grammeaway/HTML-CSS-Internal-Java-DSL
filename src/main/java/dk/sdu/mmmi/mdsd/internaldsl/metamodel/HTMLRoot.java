package dk.sdu.mmmi.mdsd.internaldsl.metamodel;

/**
 * Metamodel: The root HTML tag of the file, which all other HTMLComponents are nested within
 * 
 * @author Victor Gram Thomsen, vitho17
 */
public class HTMLRoot extends HTMLTagComponent {
    
    /**
     * Create HTMLRoot, with styling (the root should not have properties
     * @param components nested tags and styling
     */
    public HTMLRoot(HTMLComponent ... components) { 
        for(HTMLComponent component : components) { 
            
            if(component instanceof HTMLTag) {
                nestedTagList.add((HTMLTag) component);
            }
            
            if(component instanceof HTMLConfigComponent) { 
                if(((HTMLConfigComponent) component).getConfigType() == HTMLConfigComponent.TYPE.CSS) {
                    stylingList.add((HTMLConfigComponent) component);
                }   
            }
        }
        
    }       
}
