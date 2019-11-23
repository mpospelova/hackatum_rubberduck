package speechsdk.quickstart;
import java.util.HashMap;
import java.io.*;

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
		this.sourceFilepath = sourceFilepath;
		this.readme = new File(docFilePath);
	}
	
	
	//Naiver Ansatz, bekommt jedes Mal eine komplette Hashmap übergeben und erzeugt daraus das readme File im w+ Modus
	public void updateDocumentationFile(HashMap input) {
		String data = "";

		for (Object name: input.keySet()){
            String key = name.toString() + "\n";
            String value = "    "+input.get(name).toString()+"\n\n";  
            data = data + key + value;
            System.out.print(key + value);  
		} 	
		
		writeToReadme(readme, data);
	}
	
	//Besserer Ansatz, schreibt Annotations direkt in's source File.
	public void updateSourceFile(HashMap input) {
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
		test.put("Class Main", "Annotation1");
		String docFilePath = ".docFile.txt";
		
		FileWriter fw = new FileWriter(docFilePath, "");
		fw.updateDocumentationFile(test);
		
				
        //String filepath = "/home/masha/development/hackatum/hackatum_rubberduck/README.md";
        //File readme= new File(filepath);
        //String str = "\nAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
        //writeToReadme(readme, str);
    }

}
