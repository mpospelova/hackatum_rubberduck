package ByteTheDust.Rubberdocs;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class FileChooseGUI extends Application implements EventHandler<ActionEvent> {

    public static void main(String[] args) {
        launch(args);
        //SpeechToText speechToText=new SpeechToText();
        //speechToText.getUserInput();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FileChooseGUI.fxml"));

        Scene scene = new Scene(root, 600, 900);

        stage.setTitle("FXML Welcome");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void handle(ActionEvent event) {
    }
}
