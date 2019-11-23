package ByteTheDust.Rubberdocs;
import java.util.HashMap;
import java.io.*;
import java.util.Scanner;

public class FileWriter {	
	//Implements several functions to write Annotations passed by manager to Documentation File / directly to SourceFile
		
	private String sourceFilepath;
	private File readme;
	
	//Dummy method, just updates Docu-File
	public FileWriter(String docFilePath) {
		this.readme = new File(docFilePath);
	}
	
	//Secondary Constructor, also gets Path of parsed File
	public FileWriter(String docFilePath, String sourceFilePath) {
		this.sourceFilepath = sourceFilePath;
		this.readme = new File(docFilePath);
	}
	
	
	//Naiver Ansatz, bekommt jedes Mal eine komplette Hashmap übergeben und erzeugt daraus das readme File im w+ Modus
	public void updateDocumentationFile(HashMap input) {
		String data = "";

		for (Object name: input.keySet()){
            String key = name.toString() + "\n";
            String value = "    "+input.get(name).toString()+"\n\n";  
            data = data + "" + key + value;
            System.out.print(key +""+ value);
		} 	
		
		writeToReadme(readme, data);
	}


	//Besserer Ansatz, schreibt Annotations direkt in's source File.
	public void updateSourceFile(HashMap input) {
		String docuString = "";

		//liest das sourcefile als String ein
		File sourcefile = new File(sourceFilepath);
		try {
			docuString = new Scanner(sourcefile).useDelimiter("\\Z").next();
		}
		catch (java.io.FileNotFoundException e){
			e.printStackTrace();
		}

		//for alle Keywords:
		//1. Suche Keyword mit regex
		//2.






		System.out.println(docuString);



	}
	
	private static void writeToReadme(File readme, String data)  {
        OutputStream outputStream = null;
        try {
            //boolean append: file will be refreshed, every time a change is committed
            outputStream = new FileOutputStream(readme, false);
            //write(b, off, len)
            //b: data
            //off: start offset in data
            //len: length of data, number of bytes to write
            outputStream.write(data.getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}
	
	
	
	
	public static void main(String[] args) throws IOException {
		
		HashMap test = new HashMap();
		test.put("Class ABC", "Annotation1");
		test.put("public String bar()", "This Method does Foo");
		String docFilePath = ".readme.md";
		String srcFilePath = "C:\\Users\\lh\\IdeaProjects\\hackatum_rubberduck\\ByteTheDust\\src\\ByteTheDust\\Rubberdocs\\TestFile.java";

		FileWriter fw = new FileWriter(docFilePath, srcFilePath);
		fw.updateSourceFile(test);

				
        //String filepath = "/home/masha/development/hackatum/hackatum_rubberduck/README.md";
        //File readme= new File(filepath);
        //String str = "\nAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
        //writeToReadme(readme, str);
    }

}
