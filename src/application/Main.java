package application;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Objects;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main extends Application {

    static final Group group = new Group(); // group to store nodes for scene display
    static final Group group2 = new Group();
    static final Group group3 = new Group();
    static Node[][] maze;
    static Stage stage;
    //static Button button = new Button();
    public static Button back = new Button("Back");

    static int y, x;
    private static final SaveLoad saveLoad = new SaveLoad(stage);
    static AtomicBoolean solverDone = new AtomicBoolean(false);
    static AtomicBoolean mazeGenerated = new AtomicBoolean(false);
    private static final Gen gen = new Gen();
    static Button clearButton = new Button("Clear Maze");
    static Button solveButton = new Button("Solve Maze");
    static Button saveButton = new Button("Save Maze");

    @Override // entry point for javaFX
    public void start(Stage primaryStage) {

        try {
            stage = primaryStage;
            stage.setResizable(false);
            Scene scene = new Scene(group, 600, 600); // creates a new scene with group as the root, this is where you adjust window size
            Scene scene2 = new Scene(group2, 600, 600);
            Scene scene3 = new Scene(group3, 600, 600);


            saveButton.setLayoutX(400);
            saveButton.setLayoutY(550);


            Button loadButton = new Button("Load Maze");
            loadButton.setLayoutX(300);
            loadButton.setLayoutY(550);


            Button loadButton2 = new Button("Load Maze");
            loadButton2.setLayoutX(200);
            loadButton2.setLayoutY(400);
            group.getChildren().add(loadButton2);



            saveButton.setOnAction(e -> {
                SaveLoad.save(maze);
            });


            solveButton.setTranslateX(135);
            solveButton.setTranslateY(550);


            back.setTranslateX(500);
            back.setTranslateY(550);

            TextField enterRows = new TextField();
            enterRows.setTranslateX(350);
            enterRows.setTranslateY(50);


            TextField enterColumns = new TextField();
            enterColumns.setTranslateX(350);
            enterColumns.setTranslateY(100);



            clearButton.setTranslateX(225);
            clearButton.setTranslateY(550);


            Button next = new Button("Generate Maze");
            next.setTranslateX(350);
            next.setTranslateY(550);


            Label row = new Label ("Enter the number of rows, 5 - 20, for maze:");
            row.setTranslateX(100);
            row.setTranslateY(50);


            Label exception = new Label ("Error: Input must be an integer between 5 and 20");
            exception.setTranslateX(300);
            exception.setTranslateY(200);

            Label columns = new Label ("Enter number of columns, 5 - 20, for maze:");
            columns.setTranslateX(100);
            columns.setTranslateY(100);


            Button newCustom = new Button("New Custom Maze");
            newCustom.setTranslateX(250);
            newCustom.setTranslateY(550);

            newCustom.setOnAction(value -> {

                group2.getChildren().clear();
                stage.setScene(scene3);


            });

            next.setOnAction(value ->  {
                try {
                    group3.getChildren().remove(exception);
                    if((Integer.parseInt(enterColumns.getText()) >=5 && Integer.parseInt(enterColumns.getText()) <= 20) && (Integer.parseInt(enterRows.getText()) >= 5 && Integer.parseInt(enterRows.getText()) <= 20)) {

                        y = Integer.parseInt(enterColumns.getText());
                        x = Integer.parseInt(enterRows.getText());
                        stage.setScene(scene2);
                        Gen generator = new Gen();
                        maze = Gen.animateGen(group2,x ,y);
                        group2.getChildren().add(newCustom);
                        group2.getChildren().add(back);
                        group2.getChildren().add(solveButton);
                        group2.getChildren().add(saveButton);
                        enterRows.clear();
                        enterColumns.clear();
                    }
                    else {
                        group3.getChildren().add(exception);
                    }
                }catch(Exception NumberFormatException) {
                    group3.getChildren().add(exception);
                }

            });

            Button genButton = new Button("Generate Maze");
            genButton.setTranslateX(10);
            genButton.setTranslateY(550);


            genButton.setOnAction(value ->  {

                y = (int)Math.floor(Math.random() * (20 - 5 + 1) + 5);
                x = (int)Math.floor(Math.random() * (20 - 5 + 1) + 5);

                group2.getChildren().add(clearButton);
                clearButton.setDisable(true);
                Gen generator = new Gen();
                genButton.setDisable(true);
                solveButton.setDisable(true);
                back.setDisable(true);
                saveButton.setDisable(true);

                maze = Gen.animateGen(group2,x ,y);

            });

            back.setOnAction(value ->  {

                group2.getChildren().clear();
                group3.getChildren().clear();
                stage.setScene(scene);
            });

            clearButton.setOnAction(value ->  {
                group2.getChildren().clear();
                group2.getChildren().add(genButton);
                group2.getChildren().add(back);
                group2.getChildren().add(saveButton);
                group2.getChildren().add(solveButton);
                saveButton.setDisable(false);
                genButton.setDisable(false);
                solveButton.setDisable(true);
                saveButton.setDisable(true);

            });

            Button ranButton = new Button("Random Maze");
            ranButton.setTranslateX(200);
            ranButton.setTranslateY(300);
            Button custom = new Button("Custom Maze");
            custom.setTranslateX(200);
            custom.setTranslateY(350);

            custom.setOnAction(value ->  {

                group3.getChildren().add(back);
                group3.getChildren().add(enterRows);
                group3.getChildren().add(enterColumns);
                group3.getChildren().add(row);
                group3.getChildren().add(columns);
                group3.getChildren().add(next);
                stage.setScene(scene3);

            });

            Text text = new Text("""
            		
            		Welcome to the CS240 Maze Solver and Generator
            		""");
            text.setTranslateX(100);
            text.setTranslateY(100);

            group.getChildren().add(text);
            group.getChildren().add(ranButton);
            group.getChildren().add(custom);
            ranButton.setOnAction(value ->  {
                y = (int)Math.floor(Math.random() * (20 - 5 + 1) + 5);
                x = (int)Math.floor(Math.random() * (20 - 5 + 1) + 5);
                stage.setScene(scene2);
                group2.getChildren().add(clearButton);
                group2.getChildren().add(genButton);
                group2.getChildren().add(back);
                clearButton.setDisable(true);
                genButton.setDisable(true);
                back.setDisable(true);
                solveButton.setDisable(true);
                group2.getChildren().add(saveButton);
                group2.getChildren().add(solveButton);
                saveButton.setDisable(true);

                maze = Gen.animateGen(group2, x, y);

            });

            loadButton2.setOnAction(e -> {

                stage.setScene(scene2);
                Node[][] loadedMaze = SaveLoad.load();
                if (loadedMaze != null) {
                    maze = loadedMaze;
                    // Render and animate the loaded maze
                    Gen.animateLoadedMaze(group2, maze, maze.length, maze[0].length); // add this line

                    // Add all necessary buttons back to group2 after rendering
                    group2.getChildren().addAll(solveButton, loadButton, back);
                }
            });

            solveButton.setOnAction(e -> {
                Solve solver = new Solve();
                solver.animateSolve(maze, group2, solverDone);
                solveButton.setDisable(true);
                clearButton.setDisable(true);
                back.setDisable(true);

            });

            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("application.css")).toExternalForm()); // adds the CSS style sheet

            stage.setScene(scene);
            stage.show(); // displays the window*/

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
