package application;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main extends Application {

    static final Group group = new Group();
    static final Group group1 = new Group();
    static final Group group2 = new Group();
    static Node[][] maze;
    static Stage stage;
    AtomicBoolean solverDone = new AtomicBoolean(false);
    AtomicBoolean mazeGenerated = new AtomicBoolean(false);

    @Override
    public void start(Stage primaryStage) {
        try {
            stage = primaryStage;
            Scene scene = new Scene(group, 500, 500); // creates a new scene with group as the root, this is where you adjust window size
            Scene scene2 = new Scene(group2, 500, 500);
            Button button = new Button("START");
            button.setTranslateX(200);
            button.setTranslateY(300);
            Text text = new Text("""
            		
            		Welcome to the CS240 Maze Solver and Generator
            		""");
            text.setTranslateX(100);
            text.setTranslateY(100);

            group.getChildren().add(text);
            group.getChildren().add(button);

            button.setOnAction(value ->  {
                stage.setScene(scene2);
            });

            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("application.css")).toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();

            // Position the buttons
            Button generateButton = new Button("Generate Maze");
            generateButton.setLayoutX(10);
            generateButton.setLayoutY(450);

            Button solveButton = new Button("Solve Maze");
            solveButton.setLayoutX(110);
            solveButton.setLayoutY(450);

            Button clearButton = new Button("Clear Maze");
            clearButton.setLayoutX(150);
            clearButton.setLayoutY(450);

            Button saveButton = new Button("Save Maze");
            saveButton.setLayoutX(260);
            saveButton.setLayoutY(450);

            Button loadButton = new Button("Load Maze");
            loadButton.setLayoutX(340);
            loadButton.setLayoutY(450);


            clearButton.setVisible(false);

            // Add buttons to group2
            group2.getChildren().addAll(saveButton, loadButton, generateButton, solveButton, clearButton);

            // Set onAction for saveButton, loadButton, generateButton and solveButton
            saveButton.setOnAction(e -> {
                SaveLoad.save(maze);
            });

            loadButton.setOnAction(e -> {
                Node[][] loadedMaze = SaveLoad.load();

                // Get the dimensions of the loaded maze.
                assert loadedMaze != null;
                int loadedRows = loadedMaze.length;
                int loadedCols = loadedMaze[0].length;

                // Use the Gen instance to render the loaded maze.
                Gen generator = new Gen();
                generator.render(loadedRows, loadedCols, loadedMaze);
                solveButton.setDisable(false);
                clearButton.setVisible(true);
            });

            generateButton.setOnAction(e -> {

                if (group.getChildren().isEmpty()) {
                    // The group is empty
                    System.out.println("The group is empty");
                } else {
                    // The group is not empty
                    System.out.println("The group is not empty");
                }
                maze = Gen.animateGen(group);
                System.out.println(Arrays.deepToString(maze));
                mazeGenerated.set(true);
                clearButton.setVisible(true);
                solveButton.setDisable(false);

            });

            solveButton.setOnAction(e -> {
                if (mazeGenerated.get() && !solverDone.get()) {
                    Solve solver = new Solve();
                    solver.animateSolve(maze, group, solverDone);
                    solveButton.setDisable(true);
                }
            });

            clearButton.setOnAction(value ->  {
                group.getChildren().clear();
                mazeGenerated.set(false);
                solverDone.set(false);
                solveButton.setDisable(true);
                clearButton.setVisible(false);
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        maze = new Node[0][0];
        launch(args);
    }
}
