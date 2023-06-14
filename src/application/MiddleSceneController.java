package application;

import javafx.scene.control.Button; 
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

public class MiddleSceneController {

	private final Group group = new Group();
	
	Stage stage;
	Scene scene;
	IntroSceneController introScene;
	DrawMazeSceneController drawScene;
	
	public MiddleSceneController(Stage stage) {
		
		
		this.stage = stage;
		scene = new Scene(group, 600, 600);
	}
	
	public MiddleSceneController() {
		
	}
	
	// Handles the scene after customization button is pressed
	public void start() {

      try {
      	    	
      	    	
      	Label exception = new Label ("Error: Input must be an integer between 5 and 20");
         exception.setTranslateX(300);
         exception.setTranslateY(200);
         
      	
      	
      	TextField enterRows = new TextField();
         enterRows.setTranslateX(350);
         enterRows.setTranslateY(50);
         group.getChildren().add(enterRows);
      	
      	
      	TextField enterColumns = new TextField();
         enterColumns.setTranslateX(350);
         enterColumns.setTranslateY(100);
      	group.getChildren().add(enterColumns);
      	
      	Label row = new Label ("Enter the number of rows, 5 - 20, for maze:");
         row.setTranslateX(100);
         row.setTranslateY(50);
         group.getChildren().add(row);
      
         
         Label columns = new Label ("Enter number of columns, 5 - 20, for maze:");
         columns.setTranslateX(100);
         columns.setTranslateY(100);
      	group.getChildren().add(columns);
      	
      	Button next = new Button("Generate Maze");
 	  	   next.setTranslateX(350);
         next.setTranslateY(550);
      	group.getChildren().add(next);
      	
      	next.setOnAction(value ->  {
         	try {
         		   
         			group.getChildren().remove(exception);
            		if((Integer.parseInt(enterColumns.getText()) >=5 && Integer.parseInt(enterColumns.getText()) <= 20) && (Integer.parseInt(enterRows.getText()) >= 5 && Integer.parseInt(enterRows.getText()) <= 20)) {
            			
   		         	int y = Integer.parseInt(enterColumns.getText());
   			         int x = Integer.parseInt(enterRows.getText());
   			         drawScene.nextButtonAction(next, x, y);
   	  	      	   enterRows.clear();
   	  	      	   enterColumns.clear();
            		}
            		else {
            			group.getChildren().add(exception);
            			
            		}
         	}catch(Exception NumberFormatException) {
         		
 
            	group.getChildren().add(exception);
         		
            }  
        	   
         });
      	
      	Button back = new Button("Back");
      	back.setTranslateX(500);
         back.setTranslateY(550);
      	group.getChildren().add(back);
      	
      	back.setOnAction(value ->  {
           	enterColumns.clear();
           	enterRows.clear();
        	   group.getChildren().clear();
        	   introScene.showScene();
        	   group.getChildren().addAll(back, enterRows, enterColumns, row, columns, next);
         });
 
      } catch (Exception e) {
          e.printStackTrace();
      }
  }

	public void showScene() {
		
		stage.setScene(scene);
		
   }
	
	public void setScenes(IntroSceneController introScene, DrawMazeSceneController drawScene) {
		
		  this.drawScene = drawScene;
		  this.introScene = introScene;
	
	}

}
