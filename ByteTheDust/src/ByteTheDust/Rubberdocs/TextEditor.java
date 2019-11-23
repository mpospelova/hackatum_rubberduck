package ByteTheDust.Rubberdocs;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class TextEditor extends Application implements EventHandler<ActionEvent> {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("texteditor.fxml"));
        Scene scene = new Scene(root, 300, 275);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void handle(ActionEvent event) {
    }

    @FXML
    public void onEdit() {
    }

    @FXML
    public void onContinue() {
        System.exit(0);
    }

    @FXML
    public void onSave() {
    }


}
