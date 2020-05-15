package maze;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import sample.MazeApp;

public class Tile extends StackPane {
    private int row;
    private int col;
    private boolean isWall;
    private Rectangle border;

    Tile(int row, int col,int size){
        this.row = row;
        this.col = col;
        this.isWall = false;
        this.border = new Rectangle(size,size);
        border.setStroke(Color.BLACK);
        border.setFill(Color.WHITE);
        getChildren().add(border);
        setTranslateX(col*size);
        setTranslateY(row*size);


        //setOnMouseClicked(e-> changeColor());
    }
    public void setIsWall(boolean bool){ this.isWall = bool;}

    public boolean isWall(){ return isWall;}


    public void changeColor(){
            this.border.setFill(Color.BLACK);

    }
    public void changeColor(Color color){
        this.border.setFill(color);
    }
    public int row(){return row;}
    public int col(){return col;}

    public Rectangle getRectangle(){return border;}

}
