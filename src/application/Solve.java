package application;
import java.util.Objects;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Solve {

    private Animations animations;
    private int delay;

    public Solve() {

        this.animations = new Animations(); // makes new animation object
        this.delay = 0; // sets delay to O
    }

    public void start(Stage stage, Group group, Node[][]maze) {

        try {
      	  	
            Timeline timeline = new Timeline();// creates new timeline
            int delay = 0; // line visibility delay set to zero

            
            for (javafx.scene.Node node : group.getChildren()) { // for loop iterates through every node in group

                if (node instanceof Line line) { // if the current node is an instanceof line
                    // adds new frame to timeLine
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(delay), e -> line.setVisible(true)));
                    delay += 5; // sets the delay to 5 or whatever value.
                }
            }
            timeline.setOnFinished(e -> { // when maze generation animation is finished.
                 // new solve
                search(maze);  // run the solution search
                animate(); // animate solution
            });
            timeline.play(); // starts animation
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void search(Node[][] maze) {

        for (Node[] nodes : maze) { // enhanced for loop, sets all nodes in maze array to unvisited

            for (int j = 0; j < maze[0].length; j++) {

                nodes[j].setVisited(false);  // set all nodes as unvisited
            }
        }

        SolveQueue<Node> list = new SolveQueue<>(); // initializes queue and add the start node
        maze[0][0].setVisited(true);
        list.enqueue(maze[0][0]);

        while(!list.isEmpty()) { // while loop for Breadth First Search

            Node current = list.dequeue(); // polls node from the queue
            animations.addToAnimation(current, delay); // add frame for the current node to addToAnimation timeline
            delay += 15; // delay for next node

            if(current.isEnd()) { // if reached end, end loop
                break;
            }
            else {
                for(Node neighbor : current.getNeighbors()) {
                    if(neighbor.isVisited()) {  // check if the neighbor is unvisited
                        // If neighbor is above current node and no walls are blocking way, visit and add to queue
                        if(neighbor.getRow() < current.getRow() && !neighbor.isDown() && !current.isUp()) {
                            neighbor.setVisited(true);
                            list.enqueue(neighbor);
                        }
                        // If neighbor is below current node and no walls are blocking way, visit and add to queue
                        else if(neighbor.getRow() > current.getRow() && !neighbor.isUp() && !current.isDown()) {
                            neighbor.setVisited(true);
                            list.enqueue(neighbor);
                        }
                        // If neighbor is left of current node and no walls are blocking way, visit and add to queue
                        else if(neighbor.getCol() < current.getCol() && !neighbor.isRight() && !current.isLeft()) {
                            neighbor.setVisited(true);
                            list.enqueue(neighbor);
                        }
                        // If neighbor is right of current node and no walls are blocking way, visit and add to queue
                        else if(neighbor.getCol() > current.getCol() && !neighbor.isLeft() && !current.isRight()) {
                            neighbor.setVisited(true);
                            list.enqueue(neighbor);
                        }
                    }
                }
            }
        }
    }

    public void animate(){ // method for adding frame to animation with chosen delay, animates the breadth first search
        animations.playAnimation();
    }
}
