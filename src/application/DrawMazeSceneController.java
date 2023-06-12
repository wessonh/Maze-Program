package application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.concurrent.atomic.AtomicBoolean;

public class DrawMazeSceneController {
	
	private Stage stage;
	private Scene scene;
	private final Group group = new Group(); // group to store nodes for scene display
	private IntroSceneController introScene;
	private MiddleSceneController middleScene;
	private Node[][]maze;
	private Button newCustom = new Button("New Custom Maze");
	private Button solveButton = new Button("Solve Maze");
	private Button back = new Button("Back");
	private Button genButton = new Button("Generate Maze");
	private Button clearButton = new Button("Clear Maze");
	
	public DrawMazeSceneController(Stage stage) {
	
		
		this.stage = stage;
		scene = new Scene(group, 600, 600); 
	}
	
	public DrawMazeSceneController() {
		
		
	}
	
	public void start() {

		try {
	  	   
		   AtomicBoolean solverDone = new AtomicBoolean(false);
			
         
		   Button loadButton = new Button("Load Maze");
         loadButton.setLayoutX(300);
         loadButton.setLayoutY(550);
         
         genButton.setTranslateX(10);
         genButton.setTranslateY(550);
         
         newCustom.setTranslateX(250);
         newCustom.setTranslateY(550);
			
         clearButton.setTranslateX(225);
         clearButton.setTranslateY(550);
		  	
		  	
		  	solveButton.setTranslateX(135);
         solveButton.setTranslateY(550);
		  	group.getChildren().add(solveButton);
		  	
		  	Button saveButton = new Button("Save Maze");
		  	saveButton.setLayoutX(400);
         saveButton.setLayoutY(550);
		   group.getChildren().add(saveButton);
		   
		   saveButton.setOnAction(e -> {
            SaveLoad.save(maze);
         });
		   
		   
      	back.setTranslateX(500);
         back.setTranslateY(550);
		  	group.getChildren().add(back);
		  	
		  	back.setOnAction(value ->  {
        	   group.getChildren().clear();
        	   introScene.showScene();
        	   group.getChildren().addAll(back, newCustom, solveButton, saveButton);
         });
		  	
		  	newCustom.setOnAction(value -> {
         	
         	group.getChildren().clear();
         	group.getChildren().addAll(back, newCustom, solveButton, saveButton);
         	middleScene.showScene();
         });
		  	
		  	solveButton.setOnAction(e -> {
            Solve solver = new Solve();
            solver.animateSolve(maze, group, solverDone);
            //solveButton.setDisable(true);
            //back.setDisable(true);

         });
		  	
		  	genButton.setOnAction(value ->  {
          	
	         int y = (int)Math.floor(Math.random() * (20 - 5 + 1) + 5);
	         int x = (int)Math.floor(Math.random() * (20 - 5 + 1) + 5);
	         	
	         group.getChildren().add(clearButton);
	         clearButton.setDisable(true);
         	Gen generator = new Gen();
         	genButton.setDisable(true);
      	   solveButton.setDisable(true);
      	   back.setDisable(true);
      	   saveButton.setDisable(true);
  
      	   maze = Gen.animateGen(group,x ,y);
      	   
       });
	 	  	
		
		 clearButton.setOnAction(value ->  {
		     	 group.getChildren().clear();
		     	 group.getChildren().add(genButton);
		     	 group.getChildren().add(back);
		     	 group.getChildren().add(saveButton);
		     	 group.getChildren().add(solveButton);
		     	 saveButton.setDisable(false);
		     	 genButton.setDisable(false);
		     	 solveButton.setDisable(true);
		     	 saveButton.setDisable(true);
		     	 
		     	 
		     	 
	    });
		 
		 loadButton.setOnAction(e -> {
       	 
			 int x = 0;
          int y = 0;
       	 stage.setScene(scene);
       	 String file = SaveLoad.load();
       	 FileReader fileReader;
       	Node[][] loadedMaze = null;
			try {
				fileReader = new FileReader(file);
				BufferedReader bufferedReader = new BufferedReader(fileReader);
	          int rows = Integer.parseInt(bufferedReader.readLine());
	          int cols = Integer.parseInt(bufferedReader.readLine());
	          x = cols;
	          y = rows;
	           loadedMaze = SaveLoad.loadFile(file, x, y, bufferedReader, fileReader);
			} catch (IOException e1) {
            e1.printStackTrace();
        }
          
           
          
          if (loadedMaze != null) {
              maze = loadedMaze;
              Gen gen = new Gen();
              gen.render(loadedMaze.length, loadedMaze[0].length, loadedMaze, group);
              group.getChildren().clear();
              maze = gen.animateGen(group, x, y);
          
              // Add all necessary buttons back to group2 after rendering
              group.getChildren().addAll(solveButton, loadButton, back);
              solveButton.setDisable(false);
              

          }
      });
		  	
	   } catch (Exception e) {
	       e.printStackTrace();
	   }
		
		
	}
	
	public void showScene() {
		
		stage.setScene(scene);
	}
	
	public void nextButtonAction(Button next, int x, int y) {
		
		stage.setScene(scene);
		group.getChildren().add(newCustom);
	  	Gen generator = new Gen();
	 	maze = Gen.animateGen(group,x ,y);
	  	
	}
	
	public void setScenes(IntroSceneController introScene, MiddleSceneController middleScene) {
		
		  this.middleScene = middleScene;
		  this.introScene = introScene;
	}
	
	public void ranButtonAction() {
	
		int y = (int)Math.floor(Math.random() * (20 - 5 + 1) + 5);
   	int x = (int)Math.floor(Math.random() * (20 - 5 + 1) + 5);
   	group.getChildren().add(clearButton);
   	stage.setScene(scene);
   	group.getChildren().add(genButton);
   	Gen generator = new Gen();
	   maze = Gen.animateGen(group, x, y);
	   
	}
}
