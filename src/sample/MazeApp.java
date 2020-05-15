package sample;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;



public class MazeApp extends Application {
    static final int TILE_SIZE = 20;
    static final int H = 400;
    static final int W = 400;
    static final int SIDEBAR_HEIGHT = H;
    static final int SIDEBAR_WIDTH = 120;
    static final int NUM_COLUMNS = W/TILE_SIZE;
    static final int NUM_ROWS = H/TILE_SIZE;


    Maze maze = new Maze(NUM_ROWS,NUM_COLUMNS);






    private Parent createContent(){
        FlowPane root = new FlowPane();
        root.setPrefSize(SIDEBAR_WIDTH + W,H);
        SideBar sideBar = new SideBar(SIDEBAR_WIDTH,SIDEBAR_HEIGHT,this); // im not sure i want to couple the app with side bar. seems messy



        Pane mazePane = new Pane();
        mazePane.setPrefSize(W,H);
        for(int row = 0; row < NUM_ROWS; row++){
            for(int col = 0; col < NUM_COLUMNS; col++){
                Tile tile = new Tile(row,col);
                maze.grid()[row][col] = tile;
                mazePane.getChildren().add(tile);
            }
        }

        mazePane.setOnMousePressed(e->{markAsWall(e,mazePane.getLayoutX(),mazePane.getLayoutY()); });
        root.getChildren().add(sideBar);
        root.getChildren().add(mazePane);
        return root;
    }



    @Override
    public void start(Stage primaryStage) throws Exception{
        Scene scene = new Scene(createContent());

        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }






    private void markAsWall(MouseEvent e, double x, double y){

        double rowCoor =  e.getSceneY() - y;
        double colCoor =  e.getSceneX() - x;
        int row = (int) rowCoor/TILE_SIZE;
        int col = (int) colCoor/TILE_SIZE;
        maze.grid()[row][col].setIsWall(true);
        maze.print();

    }
}
