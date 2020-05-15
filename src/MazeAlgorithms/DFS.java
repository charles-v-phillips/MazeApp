package MazeAlgorithms;

import java.util.Stack;

public class DFS {
    Stack<Integer[]> path = new Stack<>();
    int [][] grid;
    boolean [][] visited;
    int[] movesRow = {1,0,-1,0};
    int[] movesCol = {0,-1,0,1};
    int rowEnd;
    int colEnd;
    public DFS(int[][] grid){
        this.grid = grid;
        this.visited = new boolean[grid.length][grid[0].length];
    }
    public DFS(int numRow, int numCol){
        grid = new int[numRow][numCol];
        visited = new boolean [numRow][numCol];


    }

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
                path.push(new Integer[]{nextRow,nextCol});
                if(dfs(nextRow,nextCol)){
                    System.out.println(nextRow + "  " + nextCol);
                    return true;}
                path.pop();
            }


        }
        System.out.println("No path found");
        return false;
    }




    public boolean dfs(int rowStart, int colStart, int rowEnd, int colEnd){
        this.rowEnd = rowEnd;
        this.colEnd = colEnd;
        return dfs(rowStart,colStart);

    }

    public static void main(String[] args) {
        DFS d = new DFS(4,4);
        d.grid[1][0] = 1;
        d.grid[1][1] = 1;
        d.grid[1][2] = 1;
        d.dfs(0,0,1,3);
        System.out.println("HI");

    }
}
