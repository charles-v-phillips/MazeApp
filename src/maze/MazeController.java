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
            Button b = (Button) o;
            if(b.getId().equals("setStartButton"))setStartButtonAction();
            else if(b.getId().equals("setEndButton"))setEndButtonAction();
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

        refreshMazePane();

    }
    private void setEndButtonAction(){
        String s = view.sideBar.setEndTextField.getText();
        String[] points = s.split(",");
        int r = Integer.parseInt(points[0]);
        int c = Integer.parseInt(points[1]);
        model.rowEnd = r;
        model.colEnd = c;
    }

    private void setStartButtonAction(){
        String s = view.sideBar.setStartTextField.getText();
        String[] points = s.split(",");
        int r = Integer.parseInt(points[0]);
        int c = Integer.parseInt(points[1]);
        model.rowStart = r;
        model.colStart = c;

    }
    private void refreshMazePane(){
        for(Node n : view.mazePane.getChildren()){
            Tile t = (Tile)n;
            int row = t.row();
            int col = t.col();
            if(model.grid()[row][col] == 0){t.changeColor(Color.WHITE);}
            if(model.grid()[row][col] == 1){t.changeColor(Color.BLACK);}
            if(row == model.rowStart && col == model.colStart){ t.changeColor(Color.BLUE);}
            if(row == model.rowEnd && col == model.colEnd){t.changeColor(Color.GREEN);}

        }

    }


}
