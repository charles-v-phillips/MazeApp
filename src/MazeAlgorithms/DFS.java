package MazeAlgorithms;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class DFS implements PathFindingAlgorithm {
    Queue<Integer[]> path = new LinkedList<>();
    int [][] grid;
    boolean [][] visited;
    int[] movesRow = {1, 0, -1, 0,1,1,-1,-1};
    int[] movesCol = {0, -1, 0, 1,1,-1,-1,1};
    int rowEnd;
    int colEnd;
    public DFS(int[][] grid){
        this.grid = grid;
        this.visited = new boolean[grid.length][grid[0].length];
    }
    /*public DFS(int numRow, int numCol){
        grid = new int[numRow][numCol];
        visited = new boolean [numRow][numCol];


    }*/

    private boolean canMove(int row, int col){
        return (row >=0
                && row <grid.length
                && col >=0
                && col < grid[0].length
                && visited[row][col] == false
                && grid[row][col] ==0);
    }

    private boolean dfs(int row, int col){
        if(row ==rowEnd && col == colEnd){
            System.out.println("path found");
            return true;
        }
        for(int k = 0; k < movesRow.length; k++){
            visited[row][col] = true;
            int nextRow = row + movesRow[k];
            int nextCol = col + movesCol[k];
            if(canMove(nextRow,nextCol)){
                path.add(new Integer[]{nextRow,nextCol});
                if(dfs(nextRow,nextCol)){
                    return true;}
                path.remove(path.size()-1);
            }


        }
        System.out.println("No path found");
        return false;
    }




    public Queue<Integer[]> dfs(int rowStart, int colStart, int rowEnd, int colEnd){
        this.rowEnd = rowEnd;
        this.colEnd = colEnd;
        dfs(rowStart,colStart);
        return path;

    }

    public static void main(String[] args) {

    }

    @Override
    public Queue<Integer[]> path(int rowStart, int colStart, int rowEnd, int colEnd) {
        return dfs(rowStart,colStart,rowEnd,colEnd);
    }
}
