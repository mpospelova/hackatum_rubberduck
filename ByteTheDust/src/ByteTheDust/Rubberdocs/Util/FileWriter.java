package ByteTheDust.Rubberdocs.Util;
import java.util.HashMap;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class FileWriter {	
	//Implements several functions to write Annotations passed by manager to Documentation File / directly to SourceFile

	private File readme;
	private File source;
	
	//Dummy method, just updates Docu-File
	public FileWriter(String docFilePath) {
		this.readme = new File(docFilePath);
	}
	
	//Secondary Constructor, also gets Path of parsed File
	public FileWriter(String docFilePath, String sourceFilePath) {
		this.source = new File(sourceFilePath);
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
		try {
			docuString = new Scanner(source).useDelimiter("\\Z").next();
		}
		catch (java.io.FileNotFoundException e){
			e.printStackTrace();
		}


		String[] linesX = docuString.split("\\r\\n");
		ArrayList<String> lines = new ArrayList<>();
		for (int index = 0; index < linesX.length; index++){
			lines.add(linesX[index]);
		}

		//for alle Keywords:
		//1. Suche Keyword mit regex
		//2.


		for (Object name: input.keySet()){
			String key = name.toString();
			key.trim();

			System.out.print("Key wird bearbeitet: " + key);

			for (int index = 0; index < lines.size(); index++){
				System.out.println("Checking: " + lines.get(index));
				System.out.println("Checking for key: " + key);

				//Falls der Key gematched wird, checke, ob es bereits einen Kommentar gibt.
				//Gehe solang zurück , bis die Kommentarsektion endet, kopiere beide teile des Arrays (vor und nach der Kommentarsektion) ersetze die Kommentarsektion durch neue Kommentare.
				if (lines.get(index).contains(key)){

					String data = lines.get(index);
					String leadingWhitespace = "";

					int index0 = 0;
					int index1 = 1;

					while (data.substring(index0, index1).matches("\\s+")){
						index1++;
					}
					leadingWhitespace = data.substring(index0,index1-1);



					System.out.println("Key gefunden: " + lines.get(index));

					/*int iterator = 1;
					if(!lines.get(index-iterator).equals("")){
						iterator = 0;
					}
					else {
						while (lines.get(index - iterator).equals("\\s+/.*")) {
							System.out.println("Removing old Comment " + lines.get(index-iterator));
							iterator++;
						}
					}*/

					//TODO implement Zeilenumbrüche bei langen Comments
					String annotation = leadingWhitespace + "//" + input.get(name).toString();
					lines.add(index, annotation);
					break;
				}
			}
		}

		String ausgabeString = "";
		for (int index = 0; index < lines.size(); index++){
			ausgabeString = ausgabeString + lines.get(index) + "\r\n";
		}

		System.out.println("String bevor verarbeitung: ");
		System.out.println(docuString);
		System.out.println("String nach verarbeitung: ");
		System.out.println(ausgabeString);
		System.out.println("test");

		writeToReadme(source, ausgabeString);
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
		test.put("class TestFile", "Annotation1, (belong to class 1)");
		test.put("public String foo()", "Hello Consti, This Method does Foo");
		String docFilePath = ".readme.md";
		String srcFilePath = "src\\ByteTheDust\\Rubberdocs\\TestFile.java";

		FileWriter fw = new FileWriter(docFilePath, srcFilePath);
		fw.updateSourceFile(test);

				
        //String filepath = "/home/masha/development/hackatum/hackatum_rubberduck/README.md";
        //File readme= new File(filepath);
        //String str = "\nAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
        //writeToReadme(readme, str);
    }

}
