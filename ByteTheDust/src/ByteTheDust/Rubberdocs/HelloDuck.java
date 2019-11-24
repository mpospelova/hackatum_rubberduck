package ByteTheDust.Rubberdocs;

import ByteTheDust.Rubberdocs.Util.FileParser;
import ByteTheDust.Rubberdocs.Util.FileWriter;
import ByteTheDust.Rubberdocs.Util.INewTranslatedtext;
import ByteTheDust.Rubberdocs.Util.SpeechToText;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
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

    private Scene sceneChooseMethod;
    private GridPane paneChooseMethod;

    private Scene sceneRecordText;
    private VBox paneRecordText;

    private final ArrayList<String> parsedKeyWords = new ArrayList<>();
    private final ArrayList<Button> keywordButtons = new ArrayList<>();

    private static String selectedKeyWord;

    private static final int MY_WIDTH = 600, MY_HEIGHT = 800;

    @FXML
    public TextField myTextArea;

    @FXML
    public ImageView myImageView;

    private Image duckImage1;
    private Image duckImage2;

    boolean listening;


    public static void main(String[] args) {
        Application.launch();
    }


    @Override
    public void start(Stage stage) {
        this.stage = stage;
        stage.setTitle("ByteTheDust Initial");
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
        }

        String filepath = file.getAbsolutePath();
        doSecondTask(stage, filepath);
    }

    public static File requestFile(final Stage stage){
        final FileChooser fileChooser = new FileChooser();
        final File selectedFile = fileChooser.showOpenDialog(stage);
        return selectedFile;
    }


    public void doSecondTask(Stage stage, String filepath){

        final String FILE_PATH=filepath;

        this.listening = false;
        stage.setTitle("ByteTheDust Rubberdocs");

        //create all the objects
        List<String> keywords=new FileParser().parseSyntax(FILE_PATH); //SRC_FILE_PATH
        fileWriter = new FileWriter("", FILE_PATH);
        //parsedKeyWords.add("Keyword 1 class X");
        //parsedKeyWords.add("Keyword 2 function Y");
        parsedKeyWords.addAll(keywords);

        ColumnConstraints colConst = new ColumnConstraints();
        colConst.setPercentWidth(100.0f);
        paneChooseMethod = new GridPane();
        paneChooseMethod.getColumnConstraints().add(colConst);


        for (int i = 0; i < parsedKeyWords.size(); i++) {
            final String s = parsedKeyWords.get(i);
            final Button button1 = new Button(s);
            keywordButtons.add(button1);
            GridPane.setConstraints(button1, 0, i);
            paneChooseMethod.getChildren().add(button1);
            button1.setOnAction((event) -> onUserChooseMethod(button1));
        }
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

        sceneChooseMethod = new Scene(paneChooseMethod, MY_WIDTH, MY_HEIGHT);
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
            System.out.println("Updating file writer with Text:"+text+" Key:"+key);
            fileWriter.updateSourceFile(map);
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
    public void onContinue(ActionEvent actionEvent) {
        System.out.println("Continue");
        //stage.setScene(sceneChooseMethod);
        //stage.show();


    }
}