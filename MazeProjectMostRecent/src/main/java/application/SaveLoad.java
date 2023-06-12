package application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.*;
import java.util.ArrayList;

public class SaveLoad {

    private static Stage stage;

    public SaveLoad(Stage stage) {
        
        SaveLoad.stage = stage;
    }

    public static void saveFile(Node[][] maze, String fileName) {
        
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            int rows = maze.length; // get rows in maze
            int cols = maze[0].length; // get columns in maze
            printWriter.println(rows); // write rows to file
            printWriter.println(cols); // cols to file

            for (int i = 0; i < rows; i++) { // loop through all nodes in maze
                for (int j = 0; j < cols; j++) {
                    Node node = maze[i][j]; // get node at i, j
                    // writes node to the text file
                    printWriter.println(node.getRow() + "," + node.getCol() + "," +
                            node.isUp() + "," + node.isDown() + "," +
                            node.isLeft() + "," + node.isRight() + "," +
                            node.isVisited() + "," + node.isEnd());
                }
            }
            printWriter.close();
            System.out.println("Maze has been saved!: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void save(Node[][] maze) { // Opens file chooser and saves the maze

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Maze");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File mazeFile = fileChooser.showSaveDialog(stage);

        if(mazeFile != null) {
            saveFile(maze, mazeFile.getAbsolutePath());
        }
    }

    public static Node[][] loadFile(String fileName) { // loads maze from text file

        Node[][] maze = null;

        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            int rows = Integer.parseInt(bufferedReader.readLine()); // reads rows from file
            int cols = Integer.parseInt(bufferedReader.readLine()); // reads cols from file
            Main.x = cols; // sets x for Main
            Main.y = rows; // sets y for Main
            maze = new Node[rows][cols]; // initialize maze

            for (int i = 0; i < rows; i++) { // loops every nodes in maze
                for (int j = 0; j < cols; j++) {
                    String[] data = bufferedReader.readLine().split(",");
                    int row = Integer.parseInt(data[0]); // parses row, col, up, down, left, right, visited, and end
                    int col = Integer.parseInt(data[1]);
                    boolean up = Boolean.parseBoolean(data[2]);
                    boolean down = Boolean.parseBoolean(data[3]);
                    boolean left = Boolean.parseBoolean(data[4]);
                    boolean right = Boolean.parseBoolean(data[5]);
                    boolean visited = Boolean.parseBoolean(data[6]);
                    boolean end = Boolean.parseBoolean(data[7]);

                    Node node = new Node(row, col); // creates new node and sets the data
                    node.setUp(up);
                    node.setDown(down);
                    node.setLeft(left);
                    node.setRight(right);
                    node.setVisited(visited);
                    node.setEnd(end);

                    maze[i][j] = node; // set node in maze
                }
            }

            // when all nodes are loaded, remake the neighbors list
            for (int i = 0; i < rows; i++) { // loops through every node in maze
                for (int j = 0; j < cols; j++) {
                    ArrayList<Node> neighbors = new ArrayList<>();
                    if(i > 0) neighbors.add(maze[i-1][j]); // add node top if exists
                    if(j > 0) neighbors.add(maze[i][j-1]); // add node left if exists
                    if(i < rows - 1) neighbors.add(maze[i+1][j]); // add node below if exists
                    if(j < cols - 1) neighbors.add(maze[i][j+1]); // add node right if exists
                    maze[i][j].setNeighbors(neighbors); // sets the neighbors of node
                }
            }
            bufferedReader.close();
            System.out.println("Maze loaded successfully: " + fileName); // success message
        } catch (IOException e) {
            e.printStackTrace();
        }
        return maze; // return loaded maze
    }

    public static Node[][] load() { // opens file chooser and loads maze

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load Maze");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));

        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            return loadFile(file.getAbsolutePath());
        }
        return null;
    }
}
