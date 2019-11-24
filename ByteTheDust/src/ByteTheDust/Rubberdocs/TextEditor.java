package ByteTheDust.Rubberdocs;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

public class TextEditor  extends Application implements INewTranslatedtext {
    Parent root;
    Scene scene;
    private static final String SRC_FILE_PATH = "src\\ByteTheDust\\Rubberdocs\\TestFile.java"; //"../ExampleCodeForRubberdoc/ExampleClass.java"
    private final FileWriter fileWriter = new FileWriter("", SRC_FILE_PATH);
    private final SpeechToText speechToText = new SpeechToText(this);
    private static String translatedText = "";
    private Object toggleButt;

    @FXML
    public TextField myTextArea;
    @FXML
    public ToggleButton listeningButton;
    @FXML
    public Button saveButton;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        toggleButt = null;
        root = FXMLLoader.load(getClass().getResource("SceneRecordText.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void onBack() {
        System.exit(0);
    }

    @FXML
    public void onContinue() {
    }

    @FXML
    public void onSave() {
        HashMap<String, String> map = new HashMap<>();
        System.out.println("Updating file writer with:" + myTextArea.getText());
        //TODO
        map.put("AAA", getAreaText());
//        map.put((selectedButton).getText(), textArea.getText());
        fileWriter.updateSourceFile(map);
    }

    @FXML
    public void onListening() {
        if(toggleButt == null) {
            toggleButt = listeningButton;
            System.out.println("Start listening");
            myTextArea.setText("");
            speechToText.startRecognizer();
        } else {
            System.out.println("Stop listening");
            translatedText = speechToText.stopRecognizer();
           setAreaText(translatedText);
           toggleButt = null;
        }
    }

    public String getAreaText() {
        return myTextArea.getText();
    }

    public void setAreaText(String text) {
        myTextArea.setText(text);
    }

    @Override
    public void onNewTranslatedText (String text){
        myTextArea.setText("" + text);
    }
}
