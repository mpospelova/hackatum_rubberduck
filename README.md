# hackatum_rubberduck 

Instruction for setting up the JavaFx:
  1. Download JavaFx:
    http://gluonhq.com/download/javafx-13.0.1-sdk-windows/
  2. Open "Project Structure" in Intellij
  3. Go to "Libraries"
  4. Add a new "Project Library" (Java)
  5. Go to saving location of JavaFx
  6. Add the "\bin" folder as a library
  7. Go to "Edit Configurations" of any Main - File
  8. Add "--module-path "C:\Users\Consti10\javafx-sdk-13.0.1\lib" --add-modules javafx.controls,javafx.fxml" to "VM options" with your own
     path to the JavaFx \lib folder
  9. Now you can execute the Main Function.
  
- HelloDuck.java:
  ->the application itself
- FolderChooseGUI.java:
  ->opens the start page of the Application
- TestFile.java:
  ->an example file for demonstration
- EndPageGUI.java:
  ->opens the end page of the Application
