package speechsdk.quickstart;

public class Manager {
	//@author lorenz3001
	//this class implements the logic behind the program, while the other classes handle input/Output
	
	
	//private Parser parser
	//private GUI gui
	//private SpeechToText speechToText
	//private TextHandler (name?)
	private final String dummyFilePath = "test.java";
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		//1. Where is the document?
		//path = gui.getFilePath() {maybe check if it's java or not, Idee für Weiterentwicklung: Choose Parser instance at runtime (Strategy pattern) }
		
		//2. What is the Syntax of the file?
		//syntax  = parser.parseFile(path)
		
		//3. Display Syntax in GUI
		//GUI.displaySyntax(syntax)
		
		//4. Which segment do you want to document?
		//keyword = GUI.getUserInputKeyword()
		
		//5. What is you documentation
		//text = speechToText.getUserInput()
		
		//6. Write Documentation to corresponding location in Readme
		//textHandler.write({keyword:text})
		
		//7. Update GUI to the changed Readme State.
		//gui.update()
		
		
	}

}
