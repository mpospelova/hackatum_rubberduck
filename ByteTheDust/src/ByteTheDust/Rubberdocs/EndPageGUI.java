package ByteTheDust.Rubberdocs;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import static javafx.application.Application.launch;

public class EndPageGUI extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("EndPageGUI.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    //TODO andere Scene hinzufügen
    @FXML
    public void onContinue() {


    }

    @FXML
    public void onExit() {
        System.exit(0);
    }

}

