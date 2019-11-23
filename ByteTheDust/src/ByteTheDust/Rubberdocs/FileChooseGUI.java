package ByteTheDust.Rubberdocs;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class FileChooseGUI extends Application implements EventHandler<ActionEvent> {

    @Override
    public void start(Stage stage) {
        stage.setTitle("RubberDoc");

        //Parse the file from FileParser
        FileParser parser=new FileParser();
        final List<String> tmp=parser.parseSyntax("../ExampleCodeForRubberdoc/ExampleClass.java");

        for(final String s:tmp){
            System.out.println("Parser returned:"+s);
        }

        //StackPane pane=new StackPane();
        GridPane pane=new GridPane();
        ColumnConstraints colConst = new ColumnConstraints();
        colConst.setPercentWidth(100.0f);
        pane.getColumnConstraints().add(colConst);



        Scene scene = new Scene(pane, 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void handle(ActionEvent event) {
    }
}
