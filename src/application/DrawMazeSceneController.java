package application;



import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
	private Button loadButton = new Button("Load Maze");
	private SaveLoad saveLoadMethod = new SaveLoad(stage);
	private Button saveButton = new Button("Save Maze");
	private Gen generator = new Gen();
	
	public DrawMazeSceneController(Stage stage) {
		
		this.stage = stage;
		scene = new Scene(group, 600, 600); 
	}
	
	public DrawMazeSceneController() {
		
		
	}
	
	public void start() {

		try {
	  	   
		   AtomicBoolean solverDone = new AtomicBoolean(false);
		   
         
		   
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
		  	//group.getChildren().add(solveButton);
		  	
		  	
		  	saveButton.setLayoutX(400);
         saveButton.setLayoutY(550);
		   
		   
		   saveButton.setOnAction(e -> {
		   	
            saveLoadMethod.save(maze);
         });
		   
		   
      	back.setTranslateX(500);
         back.setTranslateY(550);
		  	//group.getChildren().add(back);
		  	
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
	         	
	         //group.getChildren().addAll(clearButton, back, genButton, solveButton, saveButton);
	         //clearButton.setDisable(true);
         	
         	//genButton.setDisable(true);
      	   //solveButton.setDisable(true);
      	   //back.setDisable(true);
      	   //saveButton.setDisable(true);
	        
      	   maze = generator.animateGen(group,x ,y);
      	   
       });
	 	  	
		
		 clearButton.setOnAction(value ->  {
		     	 group.getChildren().clear();
		     	 group.getChildren().addAll(genButton, back, saveButton, solveButton, clearButton);
		     	 //saveButton.setDisable(false);
		     	 //genButton.setDisable(false);
		     	 //solveButton.setDisable(true);
		     	 //saveButton.setDisable(true);
		     	 
		     	 
		     	 
	    });
		 
		 loadButton.setOnAction(e -> {
			 stage.setScene(scene);
			 group.getChildren().clear();
          Node[][] loadedMaze = saveLoadMethod.load();
          if (loadedMaze != null) {
              maze = loadedMaze;
              // animates the loaded maze
              
              generator.animateLoadedMaze(group, maze, maze.length, maze[0].length); // add this line

              // add all buttons back to group2 after displaying maze
              group.getChildren().addAll(solveButton, loadButton, back);

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
		
		group.getChildren().clear();
		stage.setScene(scene);
		group.getChildren().addAll(newCustom, back, solveButton, saveButton);
	 	maze = generator.animateGen(group,x ,y);
	  	
	}
	
	public void setScenes(IntroSceneController introScene, MiddleSceneController middleScene) {
		
		  this.middleScene = middleScene;
		  this.introScene = introScene;
	}
	
	public void ranButtonAction() {
		
		group.getChildren().clear();
		int y = (int)Math.floor(Math.random() * (20 - 5 + 1) + 5);
   	int x = (int)Math.floor(Math.random() * (20 - 5 + 1) + 5);

   	group.getChildren().addAll(clearButton, back, genButton, solveButton, saveButton);
   	stage.setScene(scene);
	   maze = generator.animateGen(group, x, y);
	   
	}
	
	public void loadButtonAction() {
		
		group.getChildren().clear();
		stage.setScene(scene);
      Node[][] loadedMaze = saveLoadMethod.load();
      if (loadedMaze != null) {
          maze = loadedMaze;
          // animates the loaded mGen gen = new Gen();
          generator.animateLoadedMaze(group, maze, maze.length, maze[0].length); // add this line
          group.getChildren().addAll(loadButton, solveButton, back);
          // add all buttons back to group2 after displaying maze
         

      }
	}
}
