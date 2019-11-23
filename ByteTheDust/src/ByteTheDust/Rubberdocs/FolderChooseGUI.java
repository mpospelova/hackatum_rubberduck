package ByteTheDust.Rubberdocs;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class FolderChooseGUI extends Application implements EventHandler<ActionEvent> {
    private Stage stage;
    private Scene scene;
    private String directoryPath;

    public FolderChooseGUI(Stage stage){
        this.directoryPath = "";
        this.stage = stage;

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("FolderChooseGUI.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.scene = new Scene(root);
        stage.setScene(this.scene);
        stage.show();
    }
    public FolderChooseGUI(){
        this.directoryPath = "";
    }

    public String getDirectoryPath(){
        return this.directoryPath;
    }

    public static void main(String[] args) {
        launch(args);
    }

    public File requestDirectory(final Stage stage){
        final DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(directoryChooser.getInitialDirectory());
        final File selectedDirectory = directoryChooser.showDialog(stage);
        if (selectedDirectory != null) {
            selectedDirectory.getAbsolutePath();
        }
        return selectedDirectory;
    }


    @FXML
    public void onDuckImageClicked(){
        System.out.println("Duck image clicked!");
        File dir = requestDirectory(stage);
        System.out.println(dir.toString());

        if(dir != null)
            ;//TODO: switch scene to filechooseGUI
    }

    @Override
    public void start(Stage stage) {
        this.stage = stage;

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("FolderChooseGUI.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.scene = new Scene(root);

        stage.setTitle("RubberDocs!");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void handle(ActionEvent event) {
    }
}
