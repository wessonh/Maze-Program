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
    static Stage stage;
    @Override // entry point for javaFX
    public void start(Stage primaryStage) {

        try {
      	  	stage = primaryStage;
            Scene scene = new Scene(group, 425, 425); // creates a new scene with group as the root, this is where you adjust window size
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("application.css")).toExternalForm()); // adds the CSS style sheet
            stage.setScene(scene); // sets primary stage to our scene
            stage.show(); // displays the window
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
   	 
   	 Gen generator = new Gen();
   	 maze = generator.create(15, 15);
   	 Solve solver = new Solve();
   	 solver.start(stage, group, maze);
   	 launch(args);
   	 
   	  // creates maze, sets rows and columns to value
    }
}

