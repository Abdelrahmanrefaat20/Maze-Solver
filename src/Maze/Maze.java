package Maze;

import java.util.ArrayList;
import java.util.List;

public class Maze {

    public Cell[][] grid;
    private int row;
    private int col;
    private Cell start;
    private Cell exit;

    public Maze(int row, int col) {
        this.row = row;
        this.col = col;
        this.grid = new Cell[row][col];
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRows() {
        return row;
    }

    public int getCols() {
        return col;
    }

    public Cell getCell(int row, int col) {
        return grid[row][col];
    }

    public Cell getStart() {
        return start;
    }

    public void setStart(Cell start) {
        this.start = start;
    }

    public Cell getExit() {
        return exit;
    }

    public void setExit(Cell exit) {
        this.exit = exit;
    }

    public boolean isInBounds(int row, int col) {
        return row >= 0 && row < grid.length && col >= 0 && col < grid[0].length;
    }

    public List<Cell> getNeighbors(Cell cell) {
        List<Cell> neighbors = new ArrayList<>();
        int r = cell.getRow();
        int c = cell.getCol();
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] d : directions) {
            int nr = r + d[0];
            int nc = c + d[1];
            if (isInBounds(nr, nc) && grid[nr][nc].isWalkable()) {
                neighbors.add(grid[nr][nc]);
            }
        }
        return neighbors;
    }

    public void markPath(List<Cell> path) {
        for (Cell c : path) {
            if (c.getType() != CellType.START && c.getType() != CellType.EXIT) {
                c.setType(CellType.PATH);
            }
        }
    }

    public void reset() {
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (grid[r][c].getType() == CellType.PATH) {
                    grid[r][c].setType(CellType.FREE);
                }
            }
        }
    }
}