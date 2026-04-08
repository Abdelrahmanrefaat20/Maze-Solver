package Maze;

import java.util.*;

public class BFSSolver implements Solver {
    private boolean[][] visited;
    Map<Cell, Cell> parent;

    @Override
    public List<Cell> solve(Maze maze) {
        visited = new boolean[maze.getRows()][maze.getCols()];
        parent = new HashMap<>();
        Queue<Cell> q = new ArrayDeque<>();
        q.add(maze.getStart());
        visited[maze.getStart().getRow()][maze.getStart().getCol()] = true;


        while (!q.isEmpty()) {
            Cell cur = q.poll();
            if (cur == maze.getExit()) {
                return buildPath(cur);
            }
            for (Cell neighbor : maze.getNeighbors(cur)) {
                if (!visited[neighbor.getRow()][neighbor.getCol()]) {
                    visited[neighbor.getRow()][neighbor.getCol()] = true;
                    parent.put(neighbor, cur);
                    q.add(neighbor);
                }
            }
        }
        return null;
    }


    public List<Cell> buildPath(Cell end) {
        List<Cell> path = new ArrayList<>();
        Cell current = end;
        while (current != null) {
            path.add(current);
            current = parent.get(current);
        }
        Collections.reverse(path);
        return path;
    }
}
