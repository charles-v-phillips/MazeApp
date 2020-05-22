package MazeAlgorithms;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS implements PathFindingAlgorithm {
    int[][] modelGrid;
    boolean[][]visited;
    int[] movesRow = {1,0,-1,0};
    int[] movesCol = {0,-1,0,1};
    //int rowEnd;
    //int colEnd;
    Queue<Integer[]> path;
    Queue<Integer[]> visitedNodesInOrder;
    BFSNode[][] nodeGrid;

    public BFS(int[][] grid){
        this.modelGrid = grid;
        this.visited = new boolean[grid.length][grid[0].length];
        path = new LinkedList<>();
        visitedNodesInOrder = new LinkedList<>();
        nodeGrid = new BFSNode[modelGrid.length][modelGrid[0].length];
        for(int row = 0; row < grid.length; row++ ){
            for(int col = 0; col < grid[0].length;col++){
                nodeGrid[row][col] = new BFSNode(row,col);
            }
        }


    }

    private boolean canMove(int row, int col){
        return (row >=0
                && row < modelGrid.length
                && col >=0
                && col < modelGrid[0].length
                && visited[row][col] == false
                && modelGrid[row][col] ==0);
    }
    private void bfs(int rowStart,int colStart,int rowEnd,int colEnd){
        Queue<BFSNode> q = new LinkedList<>();
        BFSNode start = nodeGrid[rowStart][colStart];//Integer[] start = new Integer[]{rowStart,colStart}
        start.cameFrom = null;
        visited[rowStart][colStart] = true;
        visitedNodesInOrder.add(new Integer[]{rowStart,colStart});
        q.add(start);


        while(!q.isEmpty()){
            BFSNode current = q.poll();
            if(current.row == rowEnd && current.col == colEnd){
                System.out.println("path found");
                reconstructPath(current);
                return;
            }
            for(int i = 0; i < movesRow.length; i++){
                int nextRow = current.row + movesRow[i];
                int nextCol = current.col + movesCol[i];
                if(canMove(nextRow,nextCol)){
                    BFSNode next = nodeGrid[nextRow][nextCol];
                    next.cameFrom = current;
                    q.add(next);
                    visited[nextRow][nextCol] = true;
                    visitedNodesInOrder.add(new Integer[]{nextRow,nextCol});
                }
            }
        }
        System.out.println("NO PATH FOUND");
        return;
    }


    private void reconstructPath(BFSNode node) {
        node = node.cameFrom; // just so the END node is not included in the reconstructed path
        while (node != null) {
            System.out.println(node.row + " " + node.col);
            Integer[] step = new Integer[2];
            step[0] = node.row;
            step[1] = node.col;
            path.add(step);
            node = node.cameFrom;
        }
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{{0,0,0},{1,0,1},{0,0,0}};
        BFS bfs = new BFS(grid);
        bfs.bfs(0,0,2,2);
    }

    @Override
    public List<Queue<Integer[]>> path(int rowStart, int colStart, int rowEnd, int colEnd) {
        ArrayList<Queue<Integer[]>> rV = new ArrayList<>();
        bfs(rowStart,colStart,rowEnd,colEnd);
        rV.add(path);
        rV.add(visitedNodesInOrder);
        return rV;

    }
}
