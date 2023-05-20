package application;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import static application.Animations.animateLines;

public class Gen {

    public static Node[][] create(int row, int col) {

        Node[][] maze = new Node[row][col];// initializes maze array with number of row and col

        for (int i = 0; i < row; i++) {// for loop fills row and col with nodes
            for (int k = 0; k < col; k++) {

                maze[i][k] = new Node(i, k);
            }
        }

        for (int i = 0; i < row; i++) { // for each Node in maze, add neighbors to neighbor list.
            for (int k = 0; k < col; k++) {

                Node node = maze[i][k];

                if (i > 0) { // if node isn't in first row, add node above to neighbor list
                    node.addNeighbor(maze[i - 1][k]);
                }
                if (i < row - 1) { // if node isn't in last row add node below to neighbor list
                    node.addNeighbor(maze[i + 1][k]);
                }
                if (k > 0) { // if node isn't in first column, add left node to neighbor list
                    node.addNeighbor(maze[i][k - 1]);
                }
                if (k < col - 1) { // if node isn't in last column, add right node to neighbor list
                    node.addNeighbor(maze[i][k + 1]);
                }
            }
        }
        generateMaze(row, col, maze); // calls generateMaze
        print(row, col, maze); // Calls the print method to print the generated maze.
        return maze;
    }
    private static void generateMaze(int row, int col, Node[][] maze) {
        // makes stack to keep track of visited nodes
        Stack<Node> stack = new Stack<>();
        // START NODE. sets the start node as top left. we might want to let the user able to adjust this
        Node start = maze[0][0];
        // END NODE. sets end node to bottom right
        Node end = maze[row-1][col-1];
        start.setVisited(true); // marks start node as visited
        stack.push(start); // push the start node into the stack

        while (!stack.isEmpty()) { // while stack has nodes
            Node current = stack.pop(); // get last node from stack
            List<Node> unvisitedNeighbors = new ArrayList<>(); // creates unvisitedNeighbor list
            // for each neighbor of current node
            for (Node neighbor : current.getNeighbors()) {
                // if neighbor is unvisited
                if (neighbor.isVisited()) {
                    unvisitedNeighbors.add(neighbor);
                }
            }
            // If there are unvisited neighbors
            if (!unvisitedNeighbors.isEmpty()) {
                // pick random unvisited neighbor
                Node chosen = unvisitedNeighbors.get((int) (Math.random() * unvisitedNeighbors.size()));
                chosen.setVisited(true); // mark as visited
                stack.push(current);
                stack.push(chosen);

                // determines direction of the chosen neighbor
                int rowDiff = chosen.getRow() - current.getRow();
                int colDiff = chosen.getCol() - current.getCol();
                // based off the direction, remove wall between current node and neighbor
                if (rowDiff == -1) {
                    chosen.setDown(false);
                    current.setUp(false);
                } else if (rowDiff == 1) {
                    chosen.setUp(false);
                    current.setDown(false);
                } else if (colDiff == -1) {
                    chosen.setRight(false);
                    current.setLeft(false);
                } else if (colDiff == 1) {
                    chosen.setLeft(false);
                    current.setRight(false);
                }
            }
        }

        if (end.isVisited()) { // Check if end node has been visited
            return;
        }
        start.setUp(false); // Set start and end nodes
        end.setDown(false);
        end.setEnd(true);
    }
    private static void print(int row, int col, Node[][] maze) {

        List<Line> visibleLines = new ArrayList<>();// Create list for visible lines and all visited nodes

        for (int y = 0; y < row; y++) { // for loops to print the maze rows and cols
            for (int x = 0; x < col; x++) {

                if (maze[y][x].isUp()) {
                    Line top = new Line(x * 25 + 25, y * 25 + 25, x * 25 + 25 + 25, y * 25 + 25);
                    if (y == 0) {
                        top.setStroke(Color.BLUE); // top blue border
                    } else {
                        top.setStroke(Color.RED); // else red
                    }
                    top.setVisible(false);
                    Main.group.getChildren().add(top);
                    visibleLines.add(top);

                }
                if (maze[y][x].isRight()) {
                    Line right = new Line(x * 25 + 25 + 25, y * 25 + 25, x * 25 + 25 + 25, y * 25 + 25 + 25);
                    if (x == col - 1) {
                        right.setStroke(Color.BLUE); // right blue border
                    } else {
                        right.setStroke(Color.RED); // else red
                    }
                    right.setVisible(false);
                    Main.group.getChildren().add(right);
                    visibleLines.add(right);
                }
                if (maze[y][x].isDown()) {
                    Line bottom = new Line(x * 25 + 25 + 25, y * 25 + 25 + 25, x * 25 + 25, y * 25 + 25 + 25);
                    if (y == row - 1) {
                        bottom.setStroke(Color.BLUE); // bottom blue border
                    } else {
                        bottom.setStroke(Color.RED); // else red
                    }
                    bottom.setVisible(false);
                    Main.group.getChildren().add(bottom);
                    visibleLines.add(bottom);
                }
                if (maze[y][x].isLeft()) {

                    Line left = new Line(x * 25 + 25, y * 25 + 25 + 25, x * 25 + 25, y * 25 + 25);
                    if (x == 0) {
                        left.setStroke(Color.BLUE);//  left blue border
                    } else {
                        left.setStroke(Color.RED); // else red
                    }
                    left.setVisible(false);
                    Main.group.getChildren().add(left);
                    visibleLines.add(left);
                }
            }
        }
        animateLines(visibleLines); // animates the visible lines
    }
}