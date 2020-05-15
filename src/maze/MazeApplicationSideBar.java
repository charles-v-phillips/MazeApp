package maze;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import sample.MazeApp;


public class MazeApplicationSideBar extends VBox {
    private Button button2;
    private Button setStartButton;
    private TextField setStartTextField;
    public MazeViewDelegate delegate;
    MazeApplicationSideBar(int W, int H,MazeViewDelegate delegate){
        setPrefSize(W,H);
        this.delegate = delegate;

        HBox hb = new HBox();
        setStartTextField = new TextField("start");
        setStartTextField.setPrefWidth(40);
        setStartButton = new Button("Set Start");
        setStartButton.setId("setStartButton");
        button2 = new Button("BUTTON 2");
        button2.setId("button2");

        hb.getChildren().addAll(setStartTextField,setStartButton);
        getChildren().addAll(hb,button2);

        //setStartButton.setOnMouseClicked(e-> handleStartButton(e));
        setStartButton.addEventHandler(MouseEvent.MOUSE_CLICKED,e->{
            delegate.handleEvent(e);
        });
        button2.addEventHandler(MouseEvent.MOUSE_CLICKED,e-> delegate.handleEvent(e));


    }

    /*
    private void handleStartButton(MouseEvent e){
        String s = setStartTextField.getText();
        String[] points = s.split(",");
        int r = Integer.parseInt(points[0]);
        int c = Integer.parseInt(points[1]);
        //app.maze.grid()[r][c].getRectangle().setFill(Color.BLUE);


    }

     */

    private void createButton(String text){
        Button button  = new Button(text);
        getChildren().add(button);

    }


}
