package speechsdk.quickstart;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseException;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.io.FileInputStream;
import java.io.IOException;

public class FileParser implements Parser{


    @Override
    public String parseSyntax(String docpath) throws IOException, ParseException {

        // creates an input stream for the file to be parsed
        FileInputStream in = new FileInputStream(docpath);

        CompilationUnit cu;
        try {
            // parse the file
            cu = JavaParser.parse(in);
        } finally {
            in.close();
        }

        // visit and print the methods names
//        List<String> methods = new ArrayList<>();
        new MethodVisitor().visit(cu, null);

//        visit(cu, null);


        return null;
    }

    private static class MethodVisitor extends VoidVisitorAdapter {

        public void visit(MethodDeclaration n, Object arg) {
            // here you can access the attributes of the method.
            // this method will be called for all methods in this
            // CompilationUnit, including inner class methods
            System.out.println(n.getDeclarationAsString());
        }
    }
//    public List<String> visit(MethodDeclaration n, Object arg) {
//        List<String> methods = new ArrayList<>();
//        // here you can access the attributes of the method.
//        // this method will be called for all methods in this
//        // CompilationUnit, including inner class methods
//        methods.add(n.getDeclarationAsString());
//
//        return methods;
//    }
}
