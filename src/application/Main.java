package application;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.Objects;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import javafx.scene.Parent;
import javafx.scene.text.Text;
public class Main extends Application {

    static final Group group = new Group(); // group to store nodes for scene display
    static final Group group2 = new Group();
    static Node[][] maze;
    static Stage stage;
    static Button button = new Button();
    
    @Override // entry point for javaFX
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

            
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("application.css")).toExternalForm()); // adds the CSS style sheet
      	  	
      	  		
      	  
            
            
            stage.setScene(scene);
            stage.show(); // displays the window*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public static void main(String[] args) {
   	
	   	 Button button = new Button("Generate Maze");
	 	  	 button.setTranslateX(10);
	       button.setTranslateY(450);
	       group2.getChildren().add(button);
	       Button button2 = new Button("Clear Maze");
	 	  	 button2.setTranslateX(200);
	       button2.setTranslateY(450);
	       button2.setVisible(false);
	       
	       
	       
	       button.setOnAction(value ->  {
	      	 group2.getChildren().add(button2);
	      	 Gen generator = new Gen();
	      	 maze = generator.create(15, 15);
	      	 Solve solver = new Solve();
	      	 solver.start(group2, maze,button2);
	      	 button.setDisable(true);
	      	 
	       });
	      
	       button2.setOnAction(value ->  {
	      	 group2.getChildren().clear();
	      	 group2.getChildren().add(button);
	      	 button.setText("Generate New Maze");
	      	 button.setDisable(false);
	      	 
	       });
	       
   	 
   	 launch(args);
   	 
    }
}
