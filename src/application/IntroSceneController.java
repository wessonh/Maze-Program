package application;

import java.util.Objects; 

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class IntroSceneController {
	
	private final Group group = new Group(); // group to store nodes for scene display
	private Stage stage;
	private Scene scene; 
	private MiddleSceneController middleScene;
	private DrawMazeSceneController drawScene;
	
	public IntroSceneController(Stage stage) {
		
	   this.stage = stage;
		scene = new Scene(group, 600, 600); 
		this.stage.setResizable(false);
	}
	
	// Handles everything seen on the introduction scene and communicates with other scenes when needed
	public void start() {

      try {
    	  
      	// Starts load maze function on introScene     
         Button loadButton2 = new Button("Load Maze");
         loadButton2.setLayoutX(200);
         loadButton2.setLayoutY(400);
         group.getChildren().add(loadButton2);
         loadButton2.setOnAction(value ->  {
         	
   	  		drawScene.loadButtonAction();
   
         });
         
         Button ranButton = new Button("Random Maze");
   	  	ranButton.setTranslateX(200);
   	  	ranButton.setTranslateY(300);
   	  	
   	  	ranButton.setOnAction(value ->  {
         	
   	  		drawScene.ranButtonAction();
   
         });
   	   
         Button custom = new Button("Custom Maze");
   	  	custom.setTranslateX(200);
         custom.setTranslateY(350);
         
         custom.setOnAction(value ->  {
   			
         	 middleScene.showScene();   
   		});
        
         
         Text text = new Text("""
         		
         		Welcome to the CS240 Maze Solver and Generator
         		""");
         
         text.setTranslateX(100);
         text.setTranslateY(100);
         
         group.getChildren().add(text);
         group.getChildren().add(ranButton);
         group.getChildren().add(custom);
         
         scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("application.css")).toExternalForm()); // adds the CSS style sheet
         
         stage.setScene(scene);
         stage.show(); // displays the window*/
    	  	
      } catch (Exception e) {
          e.printStackTrace();
      }
  }

  public void showScene() {
	  
	  stage.setScene(scene);
  }
  
  public void setScenes(MiddleSceneController middleScene, DrawMazeSceneController drawScene) {
	  
	  this.middleScene = middleScene;
	  this.drawScene = drawScene;
  }

}
