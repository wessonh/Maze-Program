package application;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.util.Duration;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Animations {

    private final Timeline timeline; 

    public Animations() {
        
       this.timeline = new Timeline(); // new Timeline
    }
    
    
    public static void animateLines(List<Line> lines) {
        
        for (Line line : lines) { // for loop sets lines to be invisible
            line.setVisible(false);
        }
        
        Timeline timeline = new Timeline(); // creates timeline for animateLines
        AtomicInteger index = new AtomicInteger(); // Create a variable to keep track of the current index
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.05), e -> { // creates frame for animation

            Line line = lines.get(index.get()); // get line at current index
            line.setVisible(true); // make the line visible
            index.getAndIncrement(); // increment the index
        });
        
        timeline.getKeyFrames().add(keyFrame);// Add key frame to the timeline
        timeline.setCycleCount(lines.size()); // sets cycle count to size of lines list
    }

    public void addToAnimation(Node current, int delay) { // method adds nodes from solver path as frames in animation
        
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(delay), e -> { // adds nodes from solver as frames
            // marks visited with *
            Text text = new Text(current.getCol() * 25 + 37.5, current.getRow() * 25 + 37.5, "*"); 
            Main.group.getChildren().add(text);
        }));
    }
    
    public void playAnimation() {
        
        timeline.play(); // play animation
    }
}
