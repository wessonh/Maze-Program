package application;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Objects;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main extends Application {
    
	
	public static void main(String[] args) {

      launch(args);
  }

    @Override // entry point for javaFX
    public void start(Stage primaryStage) {

        try {
      	  
      	  IntroSceneController introScene = new IntroSceneController(primaryStage);
      	  MiddleSceneController middleScene = new MiddleSceneController(primaryStage);
      	  DrawMazeSceneController drawScene = new DrawMazeSceneController(primaryStage);
      	  
      	  introScene.setScenes(middleScene, drawScene);
      	  middleScene.setScenes(introScene, drawScene);
      	  drawScene.setScenes(introScene, middleScene);
      	  
      	  introScene.start();
      	  middleScene.start();
      	  drawScene.start();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
}
