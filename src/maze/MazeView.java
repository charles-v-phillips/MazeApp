package maze;

import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import maze.Tile;

public class MazeView extends FlowPane {
    static final int TILE_SIZE = 20;
    static final int H = 600;
    static final int W = 1000;
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
        //https://stackoverflow.com/questions/60012383/mousedragged-detection-for-multiple-nodes-while-holding-the-button-javafx
        mazePane.setOnDragDetected(e ->{
            if(e.getButton() == MouseButton.PRIMARY){
                e.consume();
                mazePane.startFullDrag();
            }
        });
        for(int row = 0; row < NUM_ROWS; row++){
            for(int col = 0; col < NUM_COLUMNS; col++){
                Tile tile = new Tile(row,col,TILE_SIZE);
                tile.setOnMouseDragEntered( e->{ delegate.handleEvent(e); });
                tile.addEventHandler(MouseEvent.MOUSE_CLICKED,e-> delegate.handleEvent(e));
                mazePane.getChildren().add(tile);
            }
        }

        this.getChildren().addAll(sideBar,mazePane);

    }


}
