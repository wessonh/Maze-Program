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
            int rows = maze.length;
            int cols = maze[0].length;
            printWriter.println(rows);
            printWriter.println(cols);

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    Node node = maze[i][j];
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

    public static void save(Node[][] maze) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Maze");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File mazeFile = fileChooser.showSaveDialog(stage);

        if(mazeFile != null) {
            saveFile(maze, mazeFile.getAbsolutePath());
        }
    }

    public static Node[][] loadFile(String fileName) {
        Node[][] maze = null;
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            int rows = Integer.parseInt(bufferedReader.readLine());
            int cols = Integer.parseInt(bufferedReader.readLine());
            Main.x = cols;
            Main.y = rows;
            maze = new Node[rows][cols];

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    String[] data = bufferedReader.readLine().split(",");
                    int row = Integer.parseInt(data[0]);
                    int col = Integer.parseInt(data[1]);
                    boolean up = Boolean.parseBoolean(data[2]);
                    boolean down = Boolean.parseBoolean(data[3]);
                    boolean left = Boolean.parseBoolean(data[4]);
                    boolean right = Boolean.parseBoolean(data[5]);
                    boolean visited = Boolean.parseBoolean(data[6]);
                    boolean end = Boolean.parseBoolean(data[7]);

                    Node node = new Node(row, col);
                    node.setUp(up);
                    node.setDown(down);
                    node.setLeft(left);
                    node.setRight(right);
                    node.setVisited(visited);
                    node.setEnd(end);

                    maze[i][j] = node;
                }
            }

            // After loading all nodes, reconstruct the neighbors list
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    ArrayList<Node> neighbors = new ArrayList<>();
                    if(i > 0) neighbors.add(maze[i-1][j]); // Up
                    if(j > 0) neighbors.add(maze[i][j-1]); // Left
                    if(i < rows - 1) neighbors.add(maze[i+1][j]); // Down
                    if(j < cols - 1) neighbors.add(maze[i][j+1]); // Right
                    maze[i][j].setNeighbors(neighbors);
                }
            }
            bufferedReader.close();
            System.out.println("Maze loaded successfully: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return maze;
    }

    public static Node[][] load() {
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
