package maze;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import sample.MazeApp;


public class MazeApplicationSideBar extends VBox {
    public Button setStartButton;
    public TextField setStartTextField;
    public Button setEndButton;
    public TextField setEndTextField;
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


        getChildren().addAll(startHBox,endHBox);

        setStartButton.addEventHandler(MouseEvent.MOUSE_CLICKED,e->{ delegate.handleEvent(e);});
        setEndButton.addEventHandler(MouseEvent.MOUSE_CLICKED,e-> delegate.handleEvent(e));


    }

}
