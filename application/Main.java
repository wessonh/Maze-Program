package application;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.Objects;

public class Main extends Application {

    static final Group group = new Group(); // group to store nodes for scene display
    static Node[][] maze;

    @Override // entry point for javaFX
    public void start(Stage primaryStage) {

        try {
            Scene scene = new Scene(group, 425, 425); // creates a new scene with group as the root, this is where you adjust window size
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("application.css")).toExternalForm()); // adds the CSS style sheet
            primaryStage.setScene(scene); // sets primary stage to our scene
            primaryStage.show(); // displays the window

            maze = Gen.create(15, 15); // creates maze, sets rows and columns to value

            Timeline timeline = new Timeline();// creates new timeline
            int delay = 0; // line visibility delay set to zero

            
            for (javafx.scene.Node node : group.getChildren()) { // for loop iterates through every node in group

                if (node instanceof Line line) { // if the current node is an instanceof line
                    // adds new frame to timeLine
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(delay), e -> line.setVisible(true)));
                    delay += 5; // sets the delay to 5 or whatever value.
                }
            }
            timeline.setOnFinished(e -> { // when maze generation animation is finished.
                Solve solve = new Solve(); // new solve
                solve.search(maze);  // run the solution search
                solve.animate(); // animate solution
            });
            timeline.play(); // starts animation

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
   	 
   	         // sets rows and columns of maze
        
   	 launch(args);
    }
}

