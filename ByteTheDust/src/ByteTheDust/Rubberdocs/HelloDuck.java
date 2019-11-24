package ByteTheDust.Rubberdocs;

import ByteTheDust.Rubberdocs.Util.FileParser;
import ByteTheDust.Rubberdocs.Util.FileWriter;
import ByteTheDust.Rubberdocs.Util.INewTranslatedtext;
import ByteTheDust.Rubberdocs.Util.SpeechToText;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class HelloDuck extends Application implements INewTranslatedtext {
    private static final String SRC_FILE_PATH = "src\\ByteTheDust\\Rubberdocs\\TestFile.java";
    private static FileWriter fileWriter;
    private final SpeechToText speechToText = new SpeechToText(this);

    private static Stage stage;
    private static String selectedFilePath;

    private Scene sceneChooseMethod;

    private VBox vbox;

    private StackPane gridStartMenu;

    private Scene sceneRecordText;
    private VBox paneRecordText;

    private final ArrayList<String> parsedKeyWords = new ArrayList<>();
    private final ArrayList<Button> keywordButtons = new ArrayList<>();

    private static String selectedKeyWord;

    private static final int MY_WIDTH = 600, MY_HEIGHT = 800;

    @FXML
    public TextArea myTextArea;

    @FXML
    public ImageView myImageView;

    @FXML
    public CheckBox toDocCheckBox;

    @FXML
    public CheckBox toSrcCodeCheckBox;

    private Image duckImage1;
    private Image duckImage2;

    boolean listening;


    public static void main(String[] args) {
        Application.launch();
    }


    @Override
    public void start(Stage stage) {
        this.stage = stage;
        stage.setTitle("RubberDocs!");
        stage.getIcons().add(new Image(HelloDuck.class.getResourceAsStream("duck1.png")));
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FolderChooseGUI.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

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
            String filepath = file.getAbsolutePath();
            selectedFilePath=filepath;
            doSecondTask(stage, filepath);
        }

    }

    public static File requestFile(final Stage stage){
        final FileChooser fileChooser = new FileChooser();
        final File selectedFile = fileChooser.showOpenDialog(stage);
        return selectedFile;
    }


    public void doSecondTask(Stage stage, String filepath){

        final String FILE_PATH=filepath;

        this.listening = false;
        stage.setTitle("RubberDocs!");
        stage.getIcons().add(new Image(HelloDuck.class.getResourceAsStream("duck2.png")));

        //create all the objects
        List<String> keywords=new FileParser().parseSyntax(FILE_PATH); //SRC_FILE_PATH
        fileWriter = new FileWriter(FILE_PATH, FILE_PATH);
        //parsedKeyWords.add("Keyword 1 class X");
        //parsedKeyWords.add("Keyword 2 function Y");
        parsedKeyWords.addAll(keywords);

        this.gridStartMenu = new StackPane();
        vbox = new VBox(7); // 5 is the spacing between elements in the VBox
        vbox.setAlignment(Pos.CENTER);
        for (int i = 0; i < parsedKeyWords.size(); i++) {
            final String s = parsedKeyWords.get(i);
            Button button1 = new Button(s);
            button1.setFont(Font.font("Arial"));
            button1.setStyle("-fx-font-size: 20; -fx-border-radius: 0; -fx-background-color: dodgerblue");
            keywordButtons.add(button1);
            vbox.getChildren().add(button1);
            button1.setOnAction((event) -> onUserChooseMethod(button1));
        }
        gridStartMenu.getChildren().add(vbox);
        gridStartMenu.setAlignment(Pos.CENTER);
        StackPane.setAlignment(vbox, Pos.CENTER);
                  // System.out.println(getClass().getResource("duck1.png").get());
        //duckImage1 = new Image(getClass().getResource("duck1.png").getPath());
        //duckImage1 = new Image("@duck1.png");
       // myImageView.
        //duckImage2 = new Image(getClass().getResource("duck2.png").getPath());
        //String path = getClass().getResource("duck2.png").toExternalForm();
        //path = path.substring(1);
        //System.out.println(getClass().getResource("duck1.png").getPath());
        //System.out.println(path);
        //duckImage2 = new Image(path);

        gridStartMenu.setStyle("-fx-background-color: DeepSkyBlue;");

        sceneChooseMethod = new Scene(gridStartMenu, MY_WIDTH, MY_HEIGHT);
        try {
            paneRecordText= FXMLLoader.load(getClass().getResource("SceneRecordText.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        sceneRecordText = new Scene(paneRecordText);
        stage.setScene(sceneChooseMethod);
        stage.show();
    }


    //In this case we change the scene to a new View
    private void onUserChooseMethod(final Button selectedButton) {
        selectedKeyWord=selectedButton.getText();
        System.out.println("onUserChooseMethod:"+selectedKeyWord);
        stage.setScene(sceneRecordText);
        final Text text=(Text)stage.getScene().lookup("#functionNameText");
        text.setText(selectedKeyWord);
    }


    @FXML
    public void onSave() {
        final String text=getAreaText();
        final String key=selectedKeyWord;
        HashMap<String, String> map = new HashMap<>();
        map.put(key, text);
        if(text.length()>0){
            if(toSrcCodeCheckBox.isSelected()) {
                System.out.println("Updating source code writer with Text:" + text + " Key:" + key);
                fileWriter.updateSourceFile(map);
            }
            if(toDocCheckBox.isSelected()) {
                System.out.println("Updating source code writer with Text:" + text + " Key:" + key);
                fileWriter.readme = requestFile(stage);
                fileWriter.updateDocumentationFile(map);
            }
        }else{
            System.out.println("No input. Skipping");
        }
        try {
            VBox box=FXMLLoader.load(getClass().getResource("EndPageGUI.fxml"));
            stage.setScene(new Scene(box));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onListening() {
        if(!listening) {
            System.out.println("Start listening (Recognizer)");
            myTextArea.setText("");
            speechToText.startRecognizer();
            //myImageView.setImage(duckImage2);
            listening = true;
        } else {
            System.out.println("Stop listening (Recognizer)");
            final String translatedText = speechToText.stopRecognizer();
            setAreaText(translatedText);
            //myImageView.setImage(duckImage1);
            listening = false;
        }
    }

    public String getAreaText() {
        return myTextArea.getText();
    }

    public void setAreaText(final String text) {
        if(text!=null && text.length()>0 && myTextArea!=null){
            myTextArea.setText(text);
        }
    }

    @Override
    public void onNewTranslatedText (String text){
        setAreaText(text);
    }

    @FXML
    public void onContinueEditing(ActionEvent actionEvent) {
        System.out.println("Continue");
        //stage.setScene(sceneChooseMethod);
        //stage.show();
        doSecondTask(stage,selectedFilePath);
    }

    @FXML
    public void onExit(ActionEvent actionEvent) {
        System.exit(0);
    }
}