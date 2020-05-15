package sample;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends StackPane {
    private int row;
    private int col;
    private boolean isWall;
    private Rectangle border = new Rectangle(MazeApp.TILE_SIZE,MazeApp.TILE_SIZE);

    Tile(int row,int col){
        this.row = row;
        this.col = col;
        this.isWall = false;
        border.setStroke(Color.BLACK);
        border.setFill(Color.WHITE);
        getChildren().add(border);
        setTranslateX(col*MazeApp.TILE_SIZE);
        setTranslateY(row*MazeApp.TILE_SIZE);


        setOnMouseClicked(e-> changeColor());
    }
    public void setIsWall(boolean bool){ this.isWall = bool;}

    public boolean isWall(){ return isWall;}


    private void changeColor(){
            this.border.setFill(Color.BLACK);

    }

    public Rectangle getRectangle(){return border;}

}
