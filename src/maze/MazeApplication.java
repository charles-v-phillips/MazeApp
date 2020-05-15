package maze;

import javafx.application.Application;
import javafx.stage.Stage;

public class MazeApplication extends Application {
    MazeController controller;
    @Override
    public void start(Stage primaryStage) throws Exception {
        controller = new MazeController();
        controller.buildView(primaryStage);
        System.out.println("loaded");

    }

    public static void main(String[] args) {
        launch(args);
    }
}
