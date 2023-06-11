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


import java.util.concurrent.atomic.AtomicBoolean;

public class Main extends Application {
/*
    //static final Group group = new Group(); // group to store nodes for scene display
    static final Group group2 = new Group();
    
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
    
    //@Override // entry point for javaFX
    public void start(Stage primaryStage) {

        try {
      	  
      	  	
      	  	
      	  	

            
               
            saveButton.setLayoutX(400);
            saveButton.setLayoutY(550);
            

            
            
            
            Button loadButton = new Button("Load Maze");
            loadButton.setLayoutX(300);
            loadButton.setLayoutY(550);
            
            
            
            saveButton.setOnAction(e -> {
               SaveLoad.save(maze);
            });
            
            
            solveButton.setTranslateX(135);
            solveButton.setTranslateY(550);
            
            
	 	  	   back.setTranslateX(500);
	         back.setTranslateY(550);
	         
            
            
            
            
            
            
            
            clearButton.setTranslateX(225);
            clearButton.setTranslateY(550);
            
	         
	         
	         
	         	
	         
	         
	         
	         	         
	         
	         
	         
	         
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
  	         
      	  	
            
            
            
            loadButton2.setOnAction(e -> {
            	
            	stage.setScene(scene2);
               Node[][] loadedMaze = SaveLoad.load();
               if (loadedMaze != null) {
                   maze = loadedMaze;
                   gen.render(loadedMaze.length, loadedMaze[0].length, loadedMaze);
                   group2.getChildren().clear();
                   maze = Gen.animateGen(group2, x, y);
               
                   // Add all necessary buttons back to group2 after rendering
                   group2.getChildren().addAll(solveButton, loadButton, back);
       
               }
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
                   group2.getChildren().addAll(solveButton, loadButton, back);
                   solveButton.setDisable(false);
                   

               }
           });
            
            solveButton.setOnAction(e -> {
               Solve solver = new Solve();
               solver.animateSolve(maze, group2, solverDone);
               solveButton.setDisable(true);
               clearButton.setDisable(true);
               back.setDisable(true);

            });

            
            
      	  	
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    */
    
    public static void main(String[] args) {
   	 
   	 launch(args);
   	 
    }

	@Override
	public void start(Stage primaryStage) throws Exception {
  	 
  	 IntroSceneController introScene = new IntroSceneController(primaryStage);
  	 MiddleSceneController middleScene = new MiddleSceneController(primaryStage);
  	 DrawMazeSceneController drawScene = new DrawMazeSceneController(primaryStage);
    
    introScene.setScenes(middleScene, drawScene);
    middleScene.setScenes(introScene, drawScene);
    drawScene.setScenes(introScene, middleScene);
    
  	 introScene.start();
  	 middleScene.start();
  	 drawScene.start();
		
	}
}
