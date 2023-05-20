package application;
import javafx.scene.Group;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class Solve {

    private final Animations animations;
    private int delay;

    public Solve() {

        this.animations = new Animations(); // makes new animation object
        this.delay = 0; // sets delay to O
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

    // this isn't used now because of the Animations class handling the displaying this, but I am keeping it just because
    public void print(Group group, Node[][] maze) {
        int row = maze.length;
        int col = maze[0].length;
        for(int y = 0; y < row; y++) {
            for(int x = 0; x < col; x++) {
                if(maze[y][x].isVisited()) {
                    Text text = new Text(x*25 + 37.5, y*25 + 37.5, "*");
                    group.getChildren().add(text);
                }
                if(maze[y][x].isUp()) {
                    Line top = new Line(x*25 + 25,y*25 + 25,x*25+25 + 25,y*25 + 25);

                    group.getChildren().add(top);
                }
                if(maze[y][x].isRight()) {
                    Line right = new Line(x*25+25 + 25,y*25 +25,x*25+25 + 25,y*25+25 + 25);
                    group.getChildren().add(right);
                }
                if(maze[y][x].isDown()) {
                    Line bottom =new Line(x*25+25 + 25,y*25+25 + 25,x*25 + 25,y*25+25 + 25);
                    group.getChildren().add(bottom);
                }
                if(maze[y][x].isLeft()) {
                    Line left = new Line(x*25 + 25,y*25+25 + 25,x*25 + 25,y*25+25);
                    group.getChildren().add(left);
                }
            }
        }
    }
}