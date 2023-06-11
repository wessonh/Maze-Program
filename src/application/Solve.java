package application;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class Solve {

    private Animations animations = new Animations();
    private int delay;

    public Solve() {

        // makes new animation object
        this.delay = 0; // sets delay to O
    }

    public void start(Group group, Node[][]maze, Button button) {

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

    //private void evaluate(Node neighbor, Node source)
    public void search(Node[][] maze) {


        for (Node[] nodes : maze) { // enhanced for loop, sets all nodes in maze array to unvisited

            for (int j = 0; j < maze[0].length; j++) {

                nodes[j].setVisited(false); // set all nodes as unvisited
                nodes[j].distance = -1;
            }
        }

        SolveQueue<Node> list = new SolveQueue<>(); // initializes queue and add the start node
        maze[0][0].setVisited(true);
        list.enqueue(maze[0][0]);

        Node end = null;

        while(!list.isEmpty()) { // while loop for Breadth First Search

            Node current = list.dequeue(); // polls node from the queue

            if(current.isEnd()) { // if reached end, end loop
                end = current;
                break;
            }
            else {
                for(Node neighbor : current.getNeighbors()) {
                    if(neighbor.isVisited()) {  // check if the neighbor is unvisited
                        // If neighbor is above current node and no walls are blocking way, visit and add to queue
                        if(neighbor.getRow() < current.getRow() && !neighbor.isDown() && !current.isUp()) {
                            neighbor.setVisited(true);
                            list.enqueue(neighbor);
                            neighbor.distance = current.distance + 1;
                            neighbor.parent = current;
                        }
                        // If neighbor is below current node and no walls are blocking way, visit and add to queue
                        else if(neighbor.getRow() > current.getRow() && !neighbor.isUp() && !current.isDown()) {
                            neighbor.setVisited(true);
                            list.enqueue(neighbor);
                            neighbor.distance = current.distance + 1;
                            neighbor.parent = current;
                        }
                        // If neighbor is left of current node and no walls are blocking way, visit and add to queue
                        else if(neighbor.getCol() < current.getCol() && !neighbor.isRight() && !current.isLeft()) {
                            neighbor.setVisited(true);
                            list.enqueue(neighbor);
                            neighbor.distance = current.distance + 1;
                            neighbor.parent = current;
                        }
                        // If neighbor is right of current node and no walls are blocking way, visit and add to queue
                        else if(neighbor.getCol() > current.getCol() && !neighbor.isLeft() && !current.isRight()) {
                            neighbor.setVisited(true);
                            list.enqueue(neighbor);
                            neighbor.distance = current.distance + 1;
                            neighbor.parent = current;
                        }
                    }
                }
            }
        }
        Node temp = end;
        ArrayList<Node> path = new ArrayList<>();
        while (temp != null){
            path.add(temp);
            temp = temp.parent;
        }

        for(int i = path.size()-1; i >=0; i-- ) {
            animations.addToAnimation(path.get(i), delay); // add frame for the current node to addToAnimation timeline
            delay += 15; // delay for next node
        }
    }

    public void animateSolve(Node[][] maze, Group group, AtomicBoolean solverDone) {

        search(maze); // runs search method from solve
        Timeline solveTimeline = animate(); // animates the maze solver

        solveTimeline.setOnFinished(e -> {
            solverDone.set(true);

        });
        solveTimeline.play();
    }

    public Timeline animate(){ // method for adding frame to animation with chosen delay, animates the breadth first search
        Timeline timeline = new Timeline();

        // Add your animation frames to the timeline

        animations.playAnimation();

        return timeline;
    }
}
