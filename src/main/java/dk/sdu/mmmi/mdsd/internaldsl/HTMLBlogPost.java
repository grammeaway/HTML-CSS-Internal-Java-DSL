package dk.sdu.mmmi.mdsd.internaldsl;

/**
 * Example program 1, using the DSL prototype.
 * Creates a very chaotic HTML page, spouting primarily nonsense.
 * @author Victor Gram Thomsen, vitho17
 */
public class HTMLBlogPost {
    
    public static class BlogHTML extends HTML.HTMLBuilder {

        @Override
        public void build() {
            HTMLRoot( 
                styling("background-color", "#282C34"),
                tag("head", "", 
                    tag("title", "Best Blog")),
                tag("body", "", 
                    tag("h1", "The Blog, The Whole Blog, and Nothing But The Blog", 
                        styling("color", "#FF0000")
                        ),
                    tag("p", "Hello world! - and no(!) lorem ipsum for you.",
                        styling("color","#0000FF")
                        ),
                    tag("form", "", 
                        property("action", "https://tinyurl.com/28ah4h55"), 
                        tag("input", "", 
                            property("type", "submit"),
                            property("value", "Button Without Functionality (?)")
                            )
                        ),
                    tag("p", "According to all known laws of aviation, there is no way "
                            + "a bee should be able to fly. Its wings are too small to get "
                            + "its fat little body off the ground. The bee, of course, flies anyway "
                            + "because bees don't care what humans think is impossible.", 
                        styling("color", "#FFFF00"),
                        styling("font-size", "large"), 
                        styling("line-height", "1.4"), 
                        styling("width", "50%")
                        )
                )                
            );
        }
        
    }
    
    
    public static void main(String[] args) {
        HTMLBlogPost blogPost = new HTMLBlogPost();
        blogPost.page = new HTML(new BlogHTML());
    }    
    
    private HTML page;
}
