package application;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.util.Duration;
import java.util.List;

public class Animations {

    private final Timeline timeline;
 
    public Animations() {

   	 this.timeline = new Timeline(); // new Timeline
    }
    public static void animateLines(List<Line> lines) {

        // sets each line to be invisible at start
        for (Line line : lines) {
            line.setVisible(false);
        }

        Timeline timeline = new Timeline(); // creates timeline for animateLines
        int[] index = new int[1]; // uses int array to hold integer. This is because keyframe
                                  // won't let us use a simple integer because it uses a lambda function.
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.05), e -> { // creates frame for animation

            Line line = lines.get(index[0]); // get line at current index
            line.setVisible(true); // make the line visible
            index[0]++; // increment the index for next frame
        });

        timeline.getKeyFrames().add(keyFrame);// Add key frame to the timeline
        timeline.setCycleCount(lines.size()); // sets cycle count to size of lines list
    }

    public void addToAnimation(Node current, int delay) { // method adds nodes from solver path as frames in animation

        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(delay), e -> { // adds nodes from solver as frames
            // marks visited with *
            Text text = new Text(current.getCol() * 25 + 37.5, current.getRow() * 25 + 37.5, "*");
            // for nodes in group, add * to display
            Main.group2.getChildren().add(text); // for nodes in group, add * to display
        }));
    }

    public void playAnimation(Button button) {
        timeline.play(); // play animation
        timeline.setOnFinished(event -> {
      	  button.setVisible(true);
        });
    }
}
