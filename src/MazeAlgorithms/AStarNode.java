package MazeAlgorithms;

public class AStarNode {
    int row;
    int col;
    int fVal;
    int hVal;
    int gVal;
    int state;
    AStarNode cameFrom;

    public AStarNode(int state,int row,int col){
        this.state = state;
        this.row = row;
        this.col = col;
    }

    public void setFVal(int rowStart,int colStart,int rowEnd,int colEnd){
        this.gVal = Math.abs(this.row - rowStart) + Math.abs(this.col - colStart);
        this.hVal = Math.abs(this.row - rowEnd) + Math.abs(this.col - colEnd);
        this.fVal = gVal + hVal;
    }

    public void giveVals(int hval,int gval){

    }
}
