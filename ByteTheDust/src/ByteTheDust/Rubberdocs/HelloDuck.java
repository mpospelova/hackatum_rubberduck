package ByteTheDust.Rubberdocs;

import ByteTheDust.Rubberdocs.Util.FileParser;
import ByteTheDust.Rubberdocs.Util.FileWriter;
import ByteTheDust.Rubberdocs.Util.INewTranslatedtext;
import ByteTheDust.Rubberdocs.Util.SpeechToText;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class HelloDuck extends Application implements INewTranslatedtext {
    private static final String SRC_FILE_PATH = "src\\ByteTheDust\\Rubberdocs\\HelloDuck_.java";
    private final FileWriter fileWriter = new FileWriter("", SRC_FILE_PATH);
    private final SpeechToText speechToText = new SpeechToText(this);

    private Stage stage;
    private Scene sceneChooseMethod;
    private GridPane paneChooseMethod;

    private Scene sceneRecordText;
    private VBox paneRecordText;

    private final ArrayList<String> parsedKeyWords = new ArrayList<>();
    private final ArrayList<Button> keywordButtons = new ArrayList<>();

    private static String selectedKeyWord;

    private static final int MY_WIDTH = 600, MY_HEIGHT = 800;

    @FXML
    public TextField myTextArea;

    boolean listening;


    public static void main(String[] args) {
        Application.launch();
    }


    @Override
    public void start(Stage stage) {
        this.listening = false;
        this.stage = stage;
        stage.setTitle("ByteTheDust Rubberdocs");

        //create all the objects
        List<String> keywords=new FileParser().parseSyntax(SRC_FILE_PATH);
        //parsedKeyWords.add("Keyword 1 class X");
        //parsedKeyWords.add("Keyword 2 function Y");
        parsedKeyWords.addAll(keywords);

        ColumnConstraints colConst = new ColumnConstraints();
        colConst.setPercentWidth(100.0f);
        paneChooseMethod = new GridPane();
        paneChooseMethod.getColumnConstraints().add(colConst);


        for (int i = 0; i < parsedKeyWords.size(); i++) {
            final String s = parsedKeyWords.get(i);
            final Button button1 = new Button(s);
            keywordButtons.add(button1);
            GridPane.setConstraints(button1, 0, i);
            paneChooseMethod.getChildren().add(button1);
            button1.setOnAction((event) -> onUserChooseMethod(button1));
        }

        sceneChooseMethod = new Scene(paneChooseMethod, MY_WIDTH, MY_HEIGHT);
        try {
            paneRecordText= FXMLLoader.load(getClass().getResource("SceneRecordText.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        sceneRecordText = new Scene(paneRecordText);
        stage.setScene(sceneChooseMethod);
        stage.show();
    }


    //In this case we change the scene to a new View
    private void onUserChooseMethod(final Button selectedButton) {
        selectedKeyWord=selectedButton.getText();
        System.out.println("onUserChooseMethod:"+selectedKeyWord);
        stage.setScene(sceneRecordText);
    }


    @FXML
    public void onSave() {
        final String text=getAreaText();
        final String key=selectedKeyWord;
        HashMap<String, String> map = new HashMap<>();
        map.put(key, text);
        if(text.length()>0){
            System.out.println("Updating file writer with Text:"+text+" Key:"+key);
            fileWriter.updateSourceFile(map);
        }else{
            System.out.println("No input. Skipping");
        }
    }

    @FXML
    public void onListening() {
        if(!listening) {
            System.out.println("Start listening (Recognizer)");
            myTextArea.setText("");
            speechToText.startRecognizer();
            listening = true;
        } else {
            System.out.println("Stop listening (Recognizer)");
            final String translatedText = speechToText.stopRecognizer();
            setAreaText(translatedText);
            listening = false;
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
        setAreaText(text);
    }

    @FXML
    public void onContinue(ActionEvent actionEvent) {
        System.out.println("Continue");
        //stage.setScene(sceneChooseMethod);
        //stage.show();
        /*try {
            VBox box=FXMLLoader.load(getClass().getResource("EndPageGUI.fxml"));
            stage.setScene(new Scene(box));
        } catch (IOException e) {
            e.printStackTrace();
        }*/


    }
}