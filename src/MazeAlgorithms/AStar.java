package MazeAlgorithms;

import java.util.*;

public class AStar {
    int[][] modelGrid;
    AStarNode[][] grid;
    PriorityQueue<AStarNode> openSet;
    ArrayList<AStarNode> closedSet;
    Comparator<AStarNode> comparator;
    Queue<Integer[]> path;
    int rowEnd;
    int colEnd;
    int[] neighborsRow = {1, 0, -1, 0,1,1,-1,-1};
    int[] neighborsCol = {0, -1, 0, 1,1,-1,-1,1};



    public AStar(int[][] grid) {
        this.modelGrid = grid;

        this.grid = new AStarNode[grid.length][grid[0].length];

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                AStarNode n = new AStarNode(grid[row][col], row, col);
                n.gVal = Integer.MAX_VALUE;
                n.fVal = Integer.MAX_VALUE;
                this.grid[row][col] = n;

            }
        }
        comparator = new Comparator<AStarNode>() {
            @Override
            public int compare(AStarNode n1, AStarNode n2) {
                if (n1.fVal == n2.fVal) return 0;
                if (n1.fVal > n2.fVal) return 1;
                return -1;
            }
        };
        openSet = new PriorityQueue<>(comparator);
        path = new LinkedList<>();
        closedSet = new ArrayList<>();


    }

    private boolean astar(int rowStart, int colStart) {
        AStarNode start = grid[rowStart][colStart];
        start.gVal = 0;
        start.hVal = Math.abs(rowStart - rowEnd) + Math.abs(colStart - colEnd);
        start.fVal = start.gVal + start.hVal;
        start.cameFrom = null;
        //start.setFVal(rowStart, colStart, rowEnd, colEnd);
        openSet.add(start);

        while (!openSet.isEmpty()) {
            AStarNode current = openSet.poll();
            closedSet.add(current);
            if (current.row == rowEnd && current.col == colEnd) {
                System.out.println("path found");
                reconstructpath(current);
                return true;
            }

            for (int i = 0; i < neighborsRow.length; i++) {
                int nextRow = current.row + neighborsRow[i];
                int nextCol = current.col + neighborsCol[i];
                if (validNeighbor(nextRow, nextCol)) {
                    AStarNode neighbor = grid[nextRow][nextCol];
                    if (!closedSet.contains(neighbor)) {
                        int tentativeGScore = current.gVal + dVal(i);
                        if (tentativeGScore < neighbor.gVal) {
                            neighbor.cameFrom = current;
                            neighbor.gVal = tentativeGScore;
                            neighbor.hVal = Math.abs(neighbor.row - rowEnd) + Math.abs(neighbor.col - colEnd);
                            neighbor.fVal = neighbor.gVal + dVal(i);
                            if (!openSet.contains(neighbor)) {
                                openSet.add(neighbor);
                            }
                        }

                    }


                }
            }


        }
        System.out.println("path not found");
        return false;
    }
    private int dVal(int i){
        return ((Math.abs(neighborsRow[i]) - Math.abs(neighborsCol[i]) == 0))?14:10;
    }

    public Queue<Integer[]> astar(int rowStart, int colStart, int rowEnd, int colEnd) {
        this.rowEnd = rowEnd;
        this.colEnd = colEnd;
        astar(rowStart, colStart);
        return path;
    }



    private boolean validNeighbor(int row, int col) {
        if (row >= 0 && row < grid.length && col >= 0 && col < grid[0].length) {
            if (modelGrid[row][col] == 0) return true;
        }
        return false;
    }
    private void reconstructpath(AStarNode node){
        node = node.cameFrom; // just so the END node is not included in the reconstructed path
        while(node != null){
            System.out.println(node.row + " " + node.col);
             Integer[] step = new Integer[2];
             step[0] = node.row;
             step[1] = node.col;
             path.add(step);
            node = node.cameFrom;


        }


    }

    public static void main(String[] args) {
        int[][] maze = new int[5][5];
        //maze[1][1] = 1;
        AStar a = new AStar(maze);
        Queue<Integer[]> path = a.astar(0,0,2,2);
        System.out.println("FINISHED");

    }
}
