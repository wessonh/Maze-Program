package application;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
public class Main extends Application {

    static final Group group = new Group(); // group to store nodes for scene display
    static final Group group2 = new Group();
    static Node[][] maze;
    static Stage stage;
    static AtomicBoolean solverDone = new AtomicBoolean(false);
    static AtomicBoolean mazeGenerated = new AtomicBoolean(false);
    static Button button = new Button();
    private static final Gen gen = new Gen();
    private static final SaveLoad saveLoad = new SaveLoad(stage);

    @Override // entry point for javaFX
    public void start(Stage primaryStage) {

        try {

            stage = primaryStage;

            Scene scene = new Scene(group, 500, 500); // creates a new scene with group as the root, this is where you adjust window size
            Scene scene2 = new Scene(group2, 500, 500);
            Button startButton = new Button("START");
            startButton.setTranslateX(200);
            startButton.setTranslateY(300);
            Text text = new Text("""
            		
            		Welcome to the CS240 Maze Solver and Generator
            		""");
            text.setTranslateX(100);
            text.setTranslateY(100);

            group.getChildren().add(text);
            group.getChildren().add(startButton);

            startButton.setOnAction(value ->  {
                stage.setScene(scene2);
            });


            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("application.css")).toExternalForm()); // adds the CSS style sheet

            stage.setScene(scene);
            stage.show(); // displays the window

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {

        Button genButton = new Button("Generate Maze");
        genButton.setTranslateX(10);
        genButton.setTranslateY(450);
        group2.getChildren().add(genButton);

        Button solveButton = new Button("Solve Maze");
        solveButton.setTranslateX(110);
        solveButton.setTranslateY(450);
        group2.getChildren().add(solveButton);

        Button clearButton = new Button("Clear Maze");
        clearButton.setTranslateX(150);
        clearButton.setTranslateY(450);
        clearButton.setVisible(false);

        Button saveButton = new Button("Save Maze");
        saveButton.setLayoutX(260);
        saveButton.setLayoutY(450);
        group2.getChildren().add(saveButton);

        Button loadButton = new Button("Load Maze");
        loadButton.setLayoutX(340);
        loadButton.setLayoutY(450);
        group2.getChildren().add(loadButton);


        genButton.setOnAction(value ->  {
            group2.getChildren().add(clearButton);
            clearButton.setVisible(true);  // sets the clear button to visible
            saveButton.setDisable(false);  // enables the save button
            maze = Gen.animateGen(group2);
            genButton.setDisable(true); // disables gen Button
            solveButton.setDisable(false); // re-enables solve button
        });

        solveButton.setOnAction(e -> {
                Solve solver = new Solve();
                solver.animateSolve(maze, group, solverDone, solveButton);

        });

        clearButton.setOnAction(value ->  {
            group2.getChildren().clear();
            group2.getChildren().addAll(genButton, solveButton, saveButton, loadButton);
            genButton.setText("Generate New Maze");
            genButton.setDisable(false);
            clearButton.setVisible(true);  // Hide clear button after maze is cleared
            saveButton.setDisable(false);  // Disable save button after maze is cleared
            solveButton.setDisable(true);
        });

        saveButton.setOnAction(e -> {
            SaveLoad.save(maze);
        });

        loadButton.setOnAction(e -> {
            Node[][] loadedMaze = SaveLoad.load();
            if (loadedMaze != null) {
                maze = loadedMaze;
                gen.render(loadedMaze.length, loadedMaze[0].length, loadedMaze);
                group2.getChildren().clear();
                maze = Gen.animateGen(group2);

                // Add all necessary buttons back to group2 after rendering
                group2.getChildren().addAll(genButton, solveButton, saveButton, loadButton, clearButton);
                genButton.setDisable(false); // Enable gen Button
                solveButton.setDisable(false);
                clearButton.setVisible(true);

            }
        });
        
        launch(args);
    }
}
