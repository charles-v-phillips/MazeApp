package maze;

import MazeAlgorithms.AStar;
import MazeAlgorithms.BFS;
import MazeAlgorithms.DFS;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

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
            else if(b.getId().equals("DFS")) DFSButtonAction();
            else if(b.getId().equals("A*")) AStarButtonAction();
            else if(b.getId().equals("refresh")) refreshButtonAction();
            else if(b.getId().equals("BFS")) BFSButtonAction();
                }

        if(o instanceof Tile) {
            System.out.println( "YOU HIT A TILE");
            Tile t = (Tile) o;
            //TODO: Think if this is where im supposed to be updating the model.
            // should i have an updateModel function that gets called ANYTIME there is a user event?
            // for now, here will do
            int row = t.row();
            int col = t.col();
            model.grid()[row][col] = 1;
            //model.print();
        }

        refreshMazePane();

    }
    private void refreshButtonAction(){
        for(int row = 0; row < model.grid().length; row++){
            for(int col = 0; col < model.grid()[0].length; col++){
                model.grid()[row][col] = 0;

            }
            model.rowStart = -1;
            model.colStart = -1;
            model.rowEnd = -1;
            model.colEnd = -1;
        }
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

    private void DFSButtonAction(){
        System.out.println("DFS BUTTON HIT");
        if(model.colStart != -1 && model.rowStart != -1 && model.colEnd != -1 && model.rowEnd != -1){
            DFS dfs = new DFS(model.grid());
            Queue<Integer[]> path = dfs.dfs(model.rowStart,model.colStart,model.rowEnd,model.colEnd);
            List<Node> children = view.mazePane.getChildren();



                Task<Void> task = new Task<Void>() {
                    // Implement required call() method
                    @Override
                    //TODO: https://medium.com/@mglover/concurrency-in-javafx-32a5f6133d
                    protected Void call() throws Exception {
                        // Add delay code from initial attempt
                        while(path.size() > 1){
                        try {
                            Thread.sleep(20);
                        } catch (Exception e) {
                        }
                            Integer[] step = path.poll();
                            model.grid()[step[0]][step[1]] = 2;
                            for(int i = 0; i < children.size();i++){
                                Tile t = (Tile)children.get(i);
                                if(t.row() == step[0] && t.col() == step[1])Platform.runLater(()-> { t.changeColor(Color.YELLOW);});
                            }

                        };

                        // We're not interested in the return value, so return null
                        return null;
                    }
                };

// Run task in new thread
                new Thread(task).start();

            }
        }

        private void AStarButtonAction(){
            System.out.println("A* pressed");
            if(model.colStart != -1 && model.rowStart != -1 && model.colEnd != -1 && model.rowEnd != -1){
                AStar a = new AStar(model.grid());
                Queue<Integer[]> path = a.astar(model.rowStart,model.colStart,model.rowEnd,model.colEnd);
                List<Node> children = view.mazePane.getChildren();

                Task<Void> task = new Task<Void>() {
                    // Implement required call() method
                    @Override
                    //TODO: https://medium.com/@mglover/concurrency-in-javafx-32a5f6133d
                    protected Void call() throws Exception {
                        // Add delay code from initial attempt
                        while(path.size() > 1){
                            try {
                                Thread.sleep(20);
                            } catch (Exception e) {
                            }
                            Integer[] step = path.poll();
                            model.grid()[step[0]][step[1]] = 2;
                            for(int i = 0; i < children.size();i++){
                                Tile t = (Tile)children.get(i);
                                if(t.row() == step[0] && t.col() == step[1])Platform.runLater(()-> { t.changeColor(Color.YELLOW);});
                            }

                        };

                        // We're not interested in the return value, so return null
                        return null;
                    }
                };

// Run task in new thread
                new Thread(task).start();

            }

            }



    private void BFSButtonAction(){
        if(model.colStart != -1 && model.rowStart != -1 && model.colEnd != -1 && model.rowEnd != -1){
           BFS bfs = new BFS(model.grid());
           Queue<Integer[]>path = bfs.bfs(model.rowStart,model.colStart,model.rowEnd,model.colEnd);
            List<Node> children = view.mazePane.getChildren();
            Task<Void> task = new Task<Void>() {
                // Implement required call() method
                @Override
                //TODO: https://medium.com/@mglover/concurrency-in-javafx-32a5f6133d
                protected Void call() throws Exception {
                    // Add delay code from initial attempt
                    while(path.size() > 1){
                        try {
                            Thread.sleep(20);
                        } catch (Exception e) {
                        }
                        Integer[] step = path.poll();
                        model.grid()[step[0]][step[1]] = 2;
                        for(int i = 0; i < children.size();i++){
                            Tile t = (Tile)children.get(i);
                            if(t.row() == step[0] && t.col() == step[1])Platform.runLater(()-> { t.changeColor(Color.YELLOW);});
                        }

                    };

                    // We're not interested in the return value, so return null
                    return null;
                }
            };

// Run task in new thread
            new Thread(task).start();



        }

    }



    private void refreshMazePane(){
        for(Node n : view.mazePane.getChildren()){
            Tile t = (Tile)n;
            int row = t.row();
            int col = t.col();
            if(model.grid()[row][col] == 0){t.changeColor(Color.WHITE);}
            if(model.grid()[row][col] == 1){t.changeColor(Color.BLACK);}
            if(model.grid()[row][col] == 2){t.changeColor(Color.YELLOW);}
            if(row == model.rowStart && col == model.colStart){ t.changeColor(Color.BLUE);}
            if(row == model.rowEnd && col == model.colEnd){t.changeColor(Color.GREEN);}

        }

    }




}
