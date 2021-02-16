package dk.sdu.mmmi.mdsd.internaldsl;

/**
 * Example program 2, using the DSL prototype.
 * Generates a more clean-looking HTML page,
 * with a little bit of metadata defined for the page.
 * @author Victor Gram Thomsen, vitho17
 */
public class HTMLBlogPost2 {
    
    public static class BlogHTML extends HTML.HTMLBuilder {

        @Override
        public void build() {
            HTMLRoot( 
                    styling("background-color", "#fffff"), 
                    styling("color", "#282C34"), 
                    styling("font-size", "large"), 
                    styling("line-height", "1.4"), 
                    styling("font-family", "Arial, Helvetica, sans-serif"), 
                    styling("width", "50%"), 
                    styling("margin", "auto"),
                tag("head", "", 
                    tag("title", "Model-Driven Software Development, SDU | SM2-MSD"),
                    tag("meta", "", 
                        property("name", "keywords"), 
                        property("content", "Model-driven software development, SDU, SM2-MSD")
                        )
                    ),
                tag("body", "", 
                    tag("h1", "Model-Driven Software Development | SM2-MSD"),
                    tag("h2", "University of Southern Denmark, Spring 2021"),
                    tag("br", ""),
                    tag("h3", "First Assignment: Internal DSL Prototype"),
                    tag("p", "This is an internal DSL prototype, written in Java. It generates HTML, with CSS styling"),
                    tag("div", ""),
                    tag("p","... which is pretty cool.", 
                            styling("font-size", "75px"), 
                            styling("text-align", "center"), 
                            styling("opacity", "0.7")
                        ),
                    tag("p", "The DSL was handed in on the 16th of February, by ", 
                        tag("a", "Victor Gram Thomsen, vitho17.", 
                            property("href", "http://victorgram.io"), 
                            property("target", "_blank")
                            )   
                        )
                    )
                );
        }
        
    }
    
    
    public static void main(String[] args) {
        HTMLBlogPost2 blogPost = new HTMLBlogPost2();
        blogPost.page = new HTML(new BlogHTML());
    }    
    
    private HTML page;
}
