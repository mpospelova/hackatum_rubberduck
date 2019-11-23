package ByteTheDust.Rubberdocs;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseException;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileParser implements Parser {

    @Override
    public List<String> parseSyntax(String docpath){

        try{
            FileInputStream in = new FileInputStream(docpath);
            CompilationUnit cu;
            try {
                cu = JavaParser.parse(in);
            } finally {
                in.close();
            }

            MethodVisitor mv = new MethodVisitor();
            mv.visit(cu, null);
            List<String> functionnames = mv.getFunctionNames();

            return functionnames;
        }catch (IOException | ParseException e){
            System.out.println(e.toString());
        }
        return new ArrayList<>();
    }

    private class MethodVisitor extends VoidVisitorAdapter {
        List<String> functionNames;

        public MethodVisitor() {
            functionNames = new ArrayList<>();
        }

        public void visit(MethodDeclaration n, Object arg) {
            functionNames.add(n.getDeclarationAsString());

        }

        public List<String> getFunctionNames() {
            return functionNames;
        }
    }
}