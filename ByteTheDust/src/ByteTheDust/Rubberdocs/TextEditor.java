package ByteTheDust.Rubberdocs;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
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
        scene = new Scene(root);
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

    @FXML
    public TextField myTextArea;

    public void setText(String text){

    }

    @FXML
    public void initialize() {
        myTextArea.setText("AAAAAAA");
    }
}
