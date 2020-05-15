package maze;

import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MazeController implements MazeViewDelegate {
    public MazeView view;
    public MazeModel model;

    public MazeController(){

    }
    public void buildView(Stage stage){
        stage.setTitle("Maze");
        MazeView root = new MazeView();
        this.view = root;
        root.delegate = this;
        this.model = new MazeModel(root.NUM_ROWS,root.NUM_COLUMNS);
        root.build();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    //TODO: seperate all these actions into their own methods
    public void handleEvent(Event e){
        Object o = e.getSource();
        if(o instanceof Button) {
            System.out.println("BUTTON CLICKED");
            Button b = (Button) o;
            if(b.getId().equals("setStartButton")){
                for(Node n :view.mazePane.getChildren() ){
                    Tile t = (Tile) n;
                    t.changeColor(Color.RED);
                }
            }
            }


        if(o instanceof Tile) {
            System.out.println( "YOU HIT A TILE");
            Tile t = (Tile) o;
            t.changeColor();
            //TODO: Think if this is where im supposed to be updating the model.
            // should i have an updateModel function that gets called ANYTIME there is a user event?
            // for now, here will do
            int row = t.row();
            int col = t.col();
            model.grid()[row][col] = 1;
            model.print();
        }

    }
/*
    private void markAsWall(MouseEvent e, double x, double y){

        double rowCoor =  e.getSceneY() - y;
        double colCoor =  e.getSceneX() - x;
        int row = (int) rowCoor/TILE_SIZE;
        int col = (int) colCoor/TILE_SIZE;
        //maze.grid()[row][col].setIsWall(true);
        //maze.print();


    }
    */

}
