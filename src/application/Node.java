package application;
import java.util.ArrayList;
import java.util.*;

public class Node {
    
    private boolean up = true; //booleans for the walls around nodes
    private boolean down = true;
    private boolean right = true;
    private boolean left = true;
    private boolean visited; // boolean to check if node has been visited in create
    private boolean end;// boolean to check for end of the maze
    private final int row; // nodes position on maze grid
    private final int col;
    private final ArrayList<Node> neighbors = new ArrayList<>(); // list stores all the neighboring node
    public Integer distance = 0;
    
    Node parent;
	 

    // constructor for Node
    public Node(int row, int col) {
        this.visited = false;
        this.col = col;
        this.row = row;
        this.end = false;
        
    }
    
    

    // getters and setters for the class variables
    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isVisited() {
        return !visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public boolean isEnd() {
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }

    public int getRow() {
        return row;
    }

//    public void setRow(int row) {
//        this.row = row;
//    }

    public int getCol() {
        return col;
    }

//    public void setCol(int col) {
//       this.col = col;
//    }

    public ArrayList<Node> getNeighbors() {
        return neighbors;
    }

//    public void setNeighbors(ArrayList<Node> neighbors) {
//        this.neighbors = neighbors;
//    }

    // adds neighbors.
    public void addNeighbor(Node node) {
       this.neighbors.add(node);
   }
}
