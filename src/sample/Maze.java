package sample;

public class Maze {
    private Tile[][] grid;
    private Tile[] start;
    private Tile[] end;
    Maze(int row,int col){
        grid = new Tile[row][col];
    }

    public Tile[][] grid(){
        return this.grid;
    }
    public void print() {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col].isWall()) System.out.print(1 + " ");
                else { System.out.print("0" + " "); }
            }System.out.println();
        }System.out.println();
    }

}
