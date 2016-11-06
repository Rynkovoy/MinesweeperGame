package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.controllers.Menu;

public class Minesweeper extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Menu menu = new Menu();
        menu.mainStage(primaryStage);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
