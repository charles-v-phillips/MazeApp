package maze;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import sample.MazeApp;


public class MazeApplicationSideBar extends VBox {
    public Button setStartButton;
    public TextField setStartTextField;
    public Button setEndButton;
    public TextField setEndTextField;
    public Button DFS;
    public Button AStar;
    public Button BFS;
    public Button refresh;
    public MazeViewDelegate delegate;
    MazeApplicationSideBar(int W, int H,MazeViewDelegate delegate){
        setPrefSize(W,H);
        this.delegate = delegate;

        HBox startHBox = new HBox();
        setStartTextField = new TextField("start");
        setStartTextField.setPrefWidth(80);
        setStartButton = new Button("Set Start");
        setStartButton.setId("setStartButton");
        startHBox.getChildren().addAll(setStartTextField,setStartButton);

        HBox endHBox = new HBox();
        setEndTextField = new TextField("end");
        setEndTextField.setPrefWidth(80);
        setEndButton = new Button("Set End");
        setEndButton.setId("setEndButton");
        endHBox.getChildren().addAll(setEndTextField,setEndButton);

        DFS = new Button("DFS");
        DFS.setId("DFS");
        AStar = new Button("A*");
        AStar.setId("A*");

        BFS = new Button("BFS");
        BFS.setId("BFS");

        refresh = new Button("Refresh");
        refresh.setId("refresh");







        getChildren().addAll(startHBox,endHBox,DFS,AStar,BFS,refresh);

        setStartButton.addEventHandler(MouseEvent.MOUSE_CLICKED,e->{ delegate.handleEvent(e);});
        setEndButton.addEventHandler(MouseEvent.MOUSE_CLICKED,e-> delegate.handleEvent(e));
        DFS.addEventHandler(MouseEvent.MOUSE_CLICKED,e->delegate.handleEvent(e));
        AStar.addEventHandler(MouseEvent.MOUSE_CLICKED,e->delegate.handleEvent(e));
        BFS.addEventHandler(MouseEvent.MOUSE_CLICKED,e-> delegate.handleEvent(e));
        refresh.addEventHandler(MouseEvent.MOUSE_CLICKED, e-> delegate.handleEvent(e));


    }

}
