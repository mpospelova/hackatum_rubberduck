package ByteTheDust.Rubberdocs;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.*;
import java.io.IOException;


public class TextEditor extends Application {
    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SceneRecordText.fxml"));

        root.getChildrenUnmodifiable().get(0).setAccessibleText("AAAA");

//        System.out.println(root.getChildrenUnmodifiable().get(0));

        Scene scene = new Scene(root);
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
    protected void initialize() {
    }


}
