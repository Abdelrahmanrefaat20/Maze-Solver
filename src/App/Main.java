package Maze;
import Maze.*;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        char[][] raw = new char[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                raw[i][j] = in.next().charAt(0);
            }
        }


        int rows = raw.length;
        int cols = raw[0].length;
        Maze maze = new Maze(rows, cols);

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                CellType type;
                switch (raw[r][c]) {
                    case 'S' -> type = CellType.START;
                    case 'E' -> type = CellType.EXIT;
                    case '1' -> type = CellType.WALL;
                    default  -> type = CellType.FREE;
                }
                Cell cell = new Cell(r, c, type);
                maze.grid[r][c] = cell;
                if (type == CellType.START) maze.setStart(cell);
                if (type == CellType.EXIT)  maze.setExit(cell);
            }
        }

        System.out.println("--- Original Maze ---");
        printMaze(maze);

        BFSSolver solver = new BFSSolver();
        List<Cell> path = solver.solve(maze);

        if (path == null) {
            System.out.println("No path exists between Start and Exit!");
        } else {
            maze.markPath(path);
            System.out.println("--- Solved Maze ---");
            printMaze(maze);
            System.out.println("Path found in " + (path.size() - 1) + " steps");
        }
    }

    static void printMaze(Maze maze) {
        for (int r = 0; r < maze.getRows(); r++) {
            for (int c = 0; c < maze.getCols(); c++) {
                CellType type = maze.grid[r][c].getType();
                char ch = switch (type) {
                    case WALL  -> '1';
                    case START -> 'S';
                    case EXIT  -> 'E';
                    case PATH  -> '*';
                    default    -> '0';
                };
                System.out.print(ch + " ");
            }
            System.out.println();
        }
    }
}