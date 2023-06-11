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

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Objects;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import javafx.scene.Parent;
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
    int y, x;
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
            loadButton.setLayoutX(200);
            loadButton.setLayoutY(400);
            group.getChildren().add(loadButton);
            
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
            group3.getChildren().add(enterRows);
            
            TextField enterColumns = new TextField();
            enterColumns.setTranslateX(350);
            enterColumns.setTranslateY(100);
            group3.getChildren().add(enterColumns);
            
            
            clearButton.setTranslateX(225);
            clearButton.setTranslateY(550);
            
	         
	         Button next = new Button("Generate Maze");
	 	  	   next.setTranslateX(400);
	         next.setTranslateY(550);
	         
	         group3.getChildren().add(next);
	         	
	         	
	         Label row = new Label ("Enter the number of rows, 5 - 20, for maze:");
	         row.setTranslateX(100);
	         row.setTranslateY(50);
	         group3.getChildren().add(row);
	         
	         Label exception = new Label ("Error: Input must be an integer between 5 and 20");
	         exception.setTranslateX(300);
	         exception.setTranslateY(200);
	         
	         Label columns = new Label ("Enter number of columns, 5 - 20, for maze:");
	         columns.setTranslateX(100);
	         columns.setTranslateY(100);
	         group3.getChildren().add(columns);
	         
	         Button newCustom = new Button("New Custom Maze");
	         newCustom.setTranslateX(250);
	         newCustom.setTranslateY(550);
	         
	         newCustom.setOnAction(value -> {
	         	
	         	group2.getChildren().clear();
	         	stage.setScene(scene3);
	         	
	         	
	         });
	         
	         next.setOnAction(value ->  {
	         	try {
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
  	         	Gen generator = new Gen();
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
            
            loadButton.setOnAction(e -> {
            	
            	stage.setScene(scene2);
               Node[][] loadedMaze = SaveLoad.load();
               if (loadedMaze != null) {
                   maze = loadedMaze;
                   gen.render(loadedMaze.length, loadedMaze[0].length, loadedMaze);
                   group2.getChildren().clear();
                   maze = Gen.animateGen(group2, x, y);

                   // Add all necessary buttons back to group2 after rendering
                   group2.getChildren().addAll(genButton, solveButton, saveButton, loadButton, clearButton);
                   genButton.setDisable(false); // Enable gen Button
                   solveButton.setDisable(false);
                   clearButton.setVisible(true);

               }
           });
            
            solveButton.setOnAction(e -> {
               Solve solver = new Solve();
               solver.animateSolve(maze, group, solverDone);
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
