package ByteTheDust.Rubberdocs;

import com.microsoft.cognitiveservices.speech.SpeechRecognizer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

//Filechooser
import java.io.File;
import javafx.stage.FileChooser;
import javafx.stage.DirectoryChooser;

import java.util.ArrayList;
import java.util.List;


public class HelloFX extends Application implements EventHandler<ActionEvent> {

    private final ArrayList<String> keywords=new ArrayList<>();
    private final ArrayList<Button> buttons=new ArrayList<>();
    private Button selectedButton;

    private final SpeechToText speechToText=new SpeechToText();

    public static void main(String[] args) {
        Application.launch();
        //SpeechToText speechToText=new SpeechToText();
        //speechToText.getUserInput();
    }

    public File requestDirectory(final Stage stage){
        final DirectoryChooser directoryChooser = new DirectoryChooser();
        final File selectedDirectory = directoryChooser.showDialog(stage);
        if (selectedDirectory != null) {
            selectedDirectory.getAbsolutePath();
        }
        return selectedDirectory;
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("ByteTheDust Rubberdocs");

        //Parse the file from FileParser
        FileParser parser=new FileParser();
        final List<String> tmp=parser.parseSyntax("../ExampleCodeForRubberdoc/ExampleClass.java");

        for(final String s:tmp){
            System.out.println("Parser returned:"+s);
        }
        keywords.addAll(tmp);
        keywords.add("Keyword 1 class X");
        keywords.add("Keyword 2 function Y");

        //StackPane pane=new StackPane();
        GridPane pane=new GridPane();
        ColumnConstraints colConst = new ColumnConstraints();
        colConst.setPercentWidth(100.0f);
        pane.getColumnConstraints().add(colConst);


        for(int i=0;i<keywords.size();i++){
            final String s=keywords.get(i);
            Button button1=new Button(s);
            buttons.add(button1);
            GridPane.setConstraints(button1,0,i);
            pane.getChildren().add(button1);
            button1.setOnAction(this);
        }

        Scene scene = new Scene(pane, 640, 480);
        stage.setScene(scene);
        stage.show();
    }


    @Override
    public void handle(ActionEvent event) {
        //if(event.getSource()==buttons.get(0)){
        //    System.out.println("OOH touch me right there");
        //}
        if(selectedButton==null){
            //Not listening yet, start listening for specific button (e.g. specific
        }
    }
}