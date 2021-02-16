# HTML w/ CSS DSL
A simple internal DSL written in Java. The project is made as a Maven project, but since no external libraries are used, there should be no issue running it like any Java application.

The DSL is based on the QuickNestedGUI examples, located in src/examples/gui_nested, in the https://github.com/ulrikpaghschultz/MDSD repository. It differentiates in the form of it's output, which in this case is styled HTML outputted to an HTML file. Naturally, this also means that it differs in how it builds the model, and in what the model consists of.

## Contents
The src directory contains all of the source code, which consists of a metamodel, specified in the dk.sdu.mmmi.mdsd.internaldsl.metamodel package, a builder, and two test programs, all of which are located in the dk.sdu.mmmi.mdsd.internaldsl package.

The two example programs are in the form of HTMLBlogPost and the aptly named HTMLBlogPost2.
HTMLBlogPost generates a pretty chaotic HTML page, HTMLBlogPost2 generates a more clean-looking one.

Both of them use tag properties to link to other webpages (none of which are malicious), and one of them even has some page metadata defined using properties.

The builder class is named HTML, and contains the static abstract HTMLBuilder.

## Program Output
When executed, the program builds the specified metamodel, and outputs an index.html file in the project root, i.e. InternalDSL/index.html. 

The program also outputs the HTML to the console.

Be aware that if an index.html file is already present in the project root, the program will overwrite it, no questions asked. So be sure to rename any previous output, if this should be worth keeping.