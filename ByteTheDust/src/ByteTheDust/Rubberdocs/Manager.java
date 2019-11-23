package ByteTheDust.Rubberdocs;

import java.util.Scanner;

public class Manager {
	//@author lorenz3001
	//this class implements the logic behind the program, while the other classes handle input/Output


	//private Parser parser
	//private FileChooseGUI gui
	private SpeechToText speechToText;
	//private TextHandler (name?)
	private final String dummyFilePath = "test.java";

	public Manager(){
		this.speechToText = new SpeechToText(null);
	}

	public void run(){
		//1. Where is the document?
		//path = gui.getFilePath() {maybe check if it's java or not, Idee für Weiterentwicklung: Choose Parser instance at runtime (Strategy pattern) }

		//2. What is the Syntax of the file?
		//syntax  = parser.parseFile(path)

		//3. Display Syntax in gui
		//gui.displaySyntax(syntax)

		//4. Which segment do you want to document?
		//keyword = gui.getUserInputKeyword()

		//5. What is you documentation
		speechToText = new SpeechToText(null);

		speechToText.startRecognizer();

		System.out.println("Press any key to stop");
		new Scanner(System.in).nextLine();

		speechToText.stopRecognizer();

		//6. Write Documentation to corresponding location in Readme
		//textHandler.write({keyword:text})

		//7. Update gui to the changed Readme State.
		//gui.update()
	}
	public static void main(String[] args) {
		Manager manager = new Manager();
		manager.run();
	}

}
