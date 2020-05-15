package maze;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import maze.Tile;

public class MazeView extends FlowPane {
    static final int TILE_SIZE = 20;
    static final int H = 400;
    static final int W = 400;
    static final int SIDEBAR_HEIGHT = H;
    static final int SIDEBAR_WIDTH = 200;
    static final int NUM_COLUMNS = W/TILE_SIZE;
    static final int NUM_ROWS = H/TILE_SIZE;

    public MazeViewDelegate delegate;
    public MazeApplicationSideBar sideBar;
    public Pane mazePane;

    public void build(){
        this.setPrefSize(SIDEBAR_WIDTH + W,H);
        this.sideBar = new MazeApplicationSideBar(SIDEBAR_WIDTH,SIDEBAR_HEIGHT,delegate);
        this.mazePane = new Pane();
        mazePane.setPrefSize(W,H);
        for(int row = 0; row < NUM_ROWS; row++){
            for(int col = 0; col < NUM_COLUMNS; col++){
                Tile tile = new Tile(row,col,TILE_SIZE);
                tile.addEventHandler(MouseEvent.MOUSE_CLICKED, e->{ delegate.handleEvent(e); });
                mazePane.getChildren().add(tile);
            }
        }

        //mazePane.setOnMousePressed(e->{markAsWall(e,mazePane.getLayoutX(),mazePane.getLayoutY()); });
        this.getChildren().add(sideBar);
        this.getChildren().add(mazePane);
    }


}
