package ByteTheDust.Rubberdocs;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileChooseGUI extends Application {
    private List<String> classes;
    private String folder;

    public FileChooseGUI(String folder){
        this.folder = folder;
        classes = getJavaFilesInFolder(folder);
    }
    public FileChooseGUI(){
    }

    public static List<String> getJavaFilesInFolder(String folder){
        try (Stream<Path> walk = Files.walk(Paths.get(folder))) {

            List<String> result = walk.map(x -> x.toString())
                    .filter(f -> f.endsWith(".java")).collect(Collectors.toList());

            result.forEach(System.out::println);
            return result;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static final double BUTTON_PADDING   = 5;
    private static final String DUMMY_FOLDER_PATH = "D:\\Uni\\hackatum\\hackatum_rubberduck\\ByteTheDust\\src\\ByteTheDust\\Rubberdocs";

    @Override
    public void start(Stage stage) {

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(BUTTON_PADDING));
        grid.setHgap(BUTTON_PADDING);
        grid.setVgap(BUTTON_PADDING);

        List<String> javaFiles = FileChooseGUI.getJavaFilesInFolder(DUMMY_FOLDER_PATH);

        for (int r = 0; r < javaFiles.size(); r++) {
            Button button = new Button(javaFiles.get(r));
            grid.add(button, 0, r);
        }

        ScrollPane scrollPane = new ScrollPane(grid);

        stage.setScene(new Scene(scrollPane));
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
