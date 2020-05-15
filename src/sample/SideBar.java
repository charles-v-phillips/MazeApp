package sample;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


public class SideBar extends VBox {
    MazeApp app;
    private Button setStartButton;
    private TextField setStartTextField;
    SideBar(){}
    SideBar(int W,int H, MazeApp app){
        this.app = app;
        setPrefSize(W,H);

        HBox hb = new HBox();
        setStartTextField = new TextField("start");
        setStartTextField.setPrefWidth(40);
        setStartButton = new Button("Set Start");
        hb.getChildren().addAll(setStartTextField,setStartButton);
        getChildren().add(hb);

        setStartButton.setOnMouseClicked(e-> handleStartButton(e));


    }


    private void handleStartButton(MouseEvent e){
        String s = setStartTextField.getText();
        String[] points = s.split(",");
        int r = Integer.parseInt(points[0]);
        int c = Integer.parseInt(points[1]);
        app.maze.grid()[r][c].getRectangle().setFill(Color.BLUE);


    }

    private void createButton(String text){
        Button button  = new Button(text);
        getChildren().add(button);

    }


}
