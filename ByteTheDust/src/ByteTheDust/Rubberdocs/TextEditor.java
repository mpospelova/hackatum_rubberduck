package ByteTheDust.Rubberdocs;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class TextEditor extends Application{
    Parent root;
    Scene scene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        root = FXMLLoader.load(getClass().getResource("SceneRecordText.fxml"));
        scene = new Scene(root, 300, 275);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void onBack() {
    }

    @FXML
    public void onContinue() {
        System.exit(0);
    }

    @FXML
    public void onSave() {
    }


}
