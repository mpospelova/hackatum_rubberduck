package speechsdk.quickstart;

import java.io.*;

public class JavaParser implements Parser{

  @Override
  public String parseSyntax(String docpath) throws IOException {
    // TODO Auto-generated method stub
//    CompilationUnit compilationUnit = StaticJavaParser.parse("class A { }");
    File file = new File("/home/masha/development/hackatum/hackatum_rubberduck/quickstart/src/speechsdk/quickstart/TestFile.java");

    BufferedReader br = new BufferedReader(new FileReader(file));
    String st;
    while ((st = br.readLine()) != null)
      System.out.println(st);
    return null;
  }



}
