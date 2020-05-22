package maze;
import MazeAlgorithms.AStar;
import MazeAlgorithms.BFS;
import MazeAlgorithms.DFS;
import MazeAlgorithms.PathFindingAlgorithm;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.CountDownLatch;


public class MazeController implements MazeViewDelegate {
    public MazeView view;
    public MazeModel model;

    public MazeController(){}

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
    public void handleEvent(Event e) {
        Object o = e.getSource();
        if(o instanceof Button) {
            Button b = (Button) o;
            if(b.getId().equals("setStartButton"))setStartButtonAction();
            else if(b.getId().equals("setEndButton"))setEndButtonAction();
            else if(b.getId().equals("refresh")) refreshButtonAction();
            else if(b.getId().equals("Random")) randomButtonAction();
            else if(b.getId().equals("DFS")) graphButton(new DFS(model.grid()));
            else if(b.getId().equals("A*")) graphButton(new AStar(model.grid()));
            else if(b.getId().equals("BFS")) graphButton(new BFS(model.grid()));
                }

        if(o instanceof Tile) {
            Tile t = (Tile) o;
            int row = t.row();
            int col = t.col();
            model.grid()[row][col] = (model.grid()[row][col] == 1)?0:1;
        }
        refreshMazePane();

    }
    private void graphButton(PathFindingAlgorithm alg) {
        System.out.println("using graph button");
        if(model.colStart != -1 && model.rowStart != -1 && model.colEnd != -1 && model.rowEnd != -1) {
            List<Queue<Integer[]>> paths = alg.path(model.rowStart, model.colStart, model.rowEnd, model.colEnd);
            List<Node> children = view.mazePane.getChildren();
            final CountDownLatch latch = new CountDownLatch(1);
            Task<Void> task1 = animatePathTask(paths.get(1),children,Color.RED); // animate traversal
            Task<Void> task2 = animatePathTask(paths.get(0),children,Color.YELLOW); //animate path
            Thread t1 = new Thread(task1);
            Thread t2 = new Thread(task2);

            t1.start();
            t2.start();

        }
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



    private void randomButtonAction(){
        for(int row = 0; row < model.grid().length;row++){
            for(int col = 0; col < model.grid()[0].length;col++){
                if(row == model.rowStart && col == model.colStart) {}
                else if(row == model.rowEnd && col == model.colEnd) {}
                else {
                    double random = Math.random();
                    model.grid()[row][col] = (random < .3) ? 1 : 0;
                }
            }
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

    public Task<Void> animatePathTask(Queue<Integer[]>path,List<Node> children,Color color){
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                while (path.size() > 1) {
                    try { Thread.sleep(30); } catch (Exception e) {}

                    Integer[] step = path.poll();
                    model.grid()[step[0]][step[1]] = 2;
                    for (int i = 0; i < children.size(); i++) {
                        Tile t = (Tile) children.get(i);
                        if (t.row() == step[0] && t.col() == step[1]) Platform.runLater(() -> {
                            t.changeColor(color);
                        });
                    }
                }
                return null;
            }
        };
        return task;
    }

}
