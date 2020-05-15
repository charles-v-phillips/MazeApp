package maze;

import MazeAlgorithms.DFS;

import java.util.ArrayList;

public class MazeModel {
    private int[][] grid;
    public int rowStart;
    public int colStart;
    public int rowEnd;
    public int colEnd;
    //private DFS dfs = new DFS(grid);

    MazeModel(int row,int col){
        grid = new int[row][col];
        rowStart = -1;
        colStart = -1;
        rowEnd = -1;
        colEnd = -1;
    }


    public int[][] grid(){return grid;}

    public void print() {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                System.out.print(grid[row][col] + " ");
            }System.out.println();
        }System.out.println();
    }

    public static void main(String[] args) {

    }

}
