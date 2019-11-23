package ByteTheDust.Rubberdocs;

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

import java.util.ArrayList;
import java.util.List;


public class HelloFX extends Application implements EventHandler<ActionEvent> {

    private final ArrayList<String> keywords=new ArrayList<>();
    private final ArrayList<Button> buttons=new ArrayList<>();

    public static void main(String[] args) {
        Application.launch();
        //SpeechToText speechToText=new SpeechToText();
        //speechToText.getUserInput();
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
        if(event.getSource()==buttons.get(0)){
            System.out.println("OOH touch me right there");
        }
    }
}