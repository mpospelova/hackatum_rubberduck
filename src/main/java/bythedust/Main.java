package bythedust;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

//import java.util.concurrent.Future;
//import com.microsoft.cognitiveservices.speech.*;


public class Main extends Application {

    public static void main(String[] args) {
        MyGUI myGUI;

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Label label=new Label("Hello world");

        Scene scene=new Scene(label);

        primaryStage.setScene(scene);

        primaryStage.show();
    }
}
