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
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

public class Main extends Application {

    static final Group group = new Group(); // group to store nodes for scene display
    static final Group group2 = new Group();
    static final Group group3 = new Group();
    static Node[][] maze;
    static Stage stage;
    //static Button button = new Button();
    public static Button back = new Button("Back");
    int y, x;
    
    @Override // entry point for javaFX
    public void start(Stage primaryStage) {

        try {
      	  	
      	  	
      	  	stage = primaryStage;
      	  	stage.setResizable(false);
      	  	Scene scene = new Scene(group, 600, 600); // creates a new scene with group as the root, this is where you adjust window size
            Scene scene2 = new Scene(group2, 600, 600);
            Scene scene3 = new Scene(group3, 600, 600);
            
            
	 	  	   back.setTranslateX(300);
	         back.setTranslateY(550);
	         
            TextField enterRows = new TextField();
            enterRows.setTranslateX(350);
            enterRows.setTranslateY(50);
            group3.getChildren().add(enterRows);
            
            TextField enterColumns = new TextField();
            enterColumns.setTranslateX(350);
            enterColumns.setTranslateY(100);
            group3.getChildren().add(enterColumns);
            
            Button button2 = new Button("Clear Maze");
	 	  	   button2.setTranslateX(200);
	         button2.setTranslateY(550);
	         
	         Button next = new Button("Generate Maze");
	 	  	   next.setTranslateX(400);
	         next.setTranslateY(550);
	         
	         group3.getChildren().add(next);
	         	
	         	
	         	
	         next.setOnAction(value ->  {
	         	y = Integer.parseInt(enterColumns.getText());
		         x = Integer.parseInt(enterRows.getText());
	         	stage.setScene(scene2);
  	         	Gen generator = new Gen();
  	      	   maze = generator.create(y, x);
  	      	   Solve solver = new Solve();
  	      	   solver.start(group2, maze,button2);
  	      	   group2.getChildren().add(back);
  	      	   enterRows.clear();
  	      	   enterColumns.clear();
           	   
           });
	         
	         Label row = new Label ("Enter the number of rows, 5 - 20, for maze:");
	         row.setTranslateX(100);
	         row.setTranslateY(50);
	         group3.getChildren().add(row);
	         
	         Label columns = new Label ("Enter number of columns, 5 - 20, for maze:");
	         columns.setTranslateX(100);
	         columns.setTranslateY(100);
	         group3.getChildren().add(columns);
	         
	         
	          
	         
	         
	         
	         
            Button button3 = new Button("Generate New Maze");
  	 	  	   button3.setTranslateX(10);
  	         button3.setTranslateY(550);
  	         
  	         button3.setOnAction(value ->  {
          	
  	         	y = (int)Math.floor(Math.random() * (20 - 5 + 1) + 5);
  	         	x = (int)Math.floor(Math.random() * (20 - 5 + 1) + 5);
  	         	
  	         	group2.getChildren().add(button2);
  	         	button2.setDisable(true);
	         	Gen generator = new Gen();
	         	
	  
	      	   maze = generator.create(y,x);
	      	   Solve solver = new Solve();
	      	   
	      	   solver.start(group2, maze,button2);
	      	   button3.setDisable(true);
	      	   
	      	   back.setDisable(true);
          });
  	         
  	       back.setOnAction(value ->  {
           	
           	   group2.getChildren().clear();
 	         	stage.setScene(scene);
           });
	       
	       
	      
	      
	       button2.setOnAction(value ->  {
	      	 group2.getChildren().clear();
	      	 group2.getChildren().add(button3);
	      	 group2.getChildren().add(back);
	      	 button3.setDisable(false);
	      	 
	      	 
	       });
  	         
      	  	Button button = new Button("Random Maze");
      	  	button.setTranslateX(200);
            button.setTranslateY(300);
            
            Button custom = new Button("Custom Maze");
      	  	custom.setTranslateX(200);
            custom.setTranslateY(350);
            
            custom.setOnAction(value ->  {
            	stage.setScene(scene3);
  	      	   
            });
            
            Text text = new Text("""
            		
            		Welcome to the CS240 Maze Solver and Generator
            		""");
            text.setTranslateX(100);
            text.setTranslateY(100);
            
            group.getChildren().add(text);
            group.getChildren().add(button);
            group.getChildren().add(custom);
            
            button.setOnAction(value ->  {
            	y = (int)Math.floor(Math.random() * (20 - 5 + 1) + 5);
  	         	x = (int)Math.floor(Math.random() * (20 - 5 + 1) + 5);
            	stage.setScene(scene2);
            	group2.getChildren().add(button2);
  	         	Gen generator = new Gen();
  	         	group2.getChildren().add(button3);
  	  
  	      	   maze = generator.create(y, x);
  	      	   Solve solver = new Solve();
  	      	   solver.start(group2, maze,button2);
  	      	   button2.setDisable(true);
  	      	   button3.setDisable(true);
  	      	   back.setDisable(true);
  	      	   group2.getChildren().add(back);
  	      	   
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
