package ByteTheDust.Rubberdocs;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class HelloFX extends Application implements EventHandler<ActionEvent>, INewTranslatedtext {

    private final ArrayList<String> keywords=new ArrayList<>();
    private final ArrayList<ToggleButton> buttons=new ArrayList<>();
    private Object selectedButton;
    private TextArea textArea;

    private final SpeechToText speechToText=new SpeechToText(this);

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

        final ToggleGroup group = new ToggleGroup();
        //group.selectedToggleProperty().addListener(this);


        for(int i=0;i<keywords.size();i++){
            final String s=keywords.get(i);
            ToggleButton button1=new ToggleButton(s);
            buttons.add(button1);
            GridPane.setConstraints(button1,0,i);
            pane.getChildren().add(button1);
            button1.setOnAction(this);
            button1.setToggleGroup(group);
        }

        textArea=new TextArea("Text area");
        GridPane.setConstraints(textArea,0,buttons.size());
        pane.getChildren().add(textArea);

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
            //Not listening yet, start listening for specific button (e.g. specific function)
            selectedButton=event.getSource();
            System.out.println("Start listening");
            textArea.setText("");
            speechToText.startRecognizer();
            //Disable all buttons except this one
            for(final ToggleButton button:buttons){
                if(button!=selectedButton){
                    button.setDisable(true);
                }
            }
        }else{
            //Already listening, if user clicked same button again stop listening, else do nothing
            //(We cannot listen for multiple keys concurrent)
            if(event.getSource()==selectedButton){
                System.out.println("Stop listening");
                final String translatedText=speechToText.stopRecognizer();
                textArea.setText(translatedText);
                selectedButton=null;
            }
            //re-enable all the butons
            for(final ToggleButton button:buttons){
                if(button!=selectedButton){
                    button.setDisable(false);
                }
            }
        }
    }


    @Override
    public void onNewTranslatedText(String text) {
        textArea.setText("Debug:"+text);
    }
}