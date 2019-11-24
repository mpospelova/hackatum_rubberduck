package ByteTheDust.Rubberdocs;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class FolderChooseGUI extends Application {
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
        return selectedDirectory;
    }

    public File requestFile(final Stage stage){
        final FileChooser fileChooser = new FileChooser();
        final File selectedFile = fileChooser.showOpenDialog(stage);
        return selectedFile;
    }


    @FXML
    public void onDuckImageClicked(){
        System.out.println("Duck image clicked!");
        //File dir = requestDirectory(stage);
        File file = requestFile(stage);
        if(file != null) {
            System.out.println("FolderChooseGUI:" + file.toString());
            //if(!FileChooseGUI.getJavaFilesInFolder(dir.toString()).isEmpty()){
                //TODO: switch scene to filechooseGUI
            //}
            //else{
              //  System.out.println("no java files found");
            //}
        }
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
}
