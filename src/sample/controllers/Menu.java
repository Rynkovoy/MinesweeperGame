package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Board;
import sample.Fields;

import java.io.IOException;

public class Menu extends Fields {



    public void mainStage(Stage primaryStage) throws IOException {
        stage = primaryStage;

        stage.setTitle("Minesweeper");
        stage.setResizable(false);
        Parent root = FXMLLoader.load(getClass().getResource("../interfaces/menu.fxml"));
        menuScene = new Scene(root, 500, 400);

        stage.setScene(menuScene);

        stage.show();
    }

    public void play(ActionEvent actionEvent) {
        Board board = new Board();

        stage.setScene(ingameScene);
        stage.show();
    }

    public void options(ActionEvent actionEvent) throws IOException {
        stage.setTitle("Options");
        stage.setResizable(false);
        Parent root = FXMLLoader.load(getClass().getResource("../interfaces/options.fxml"));
        optionsScene = new Scene(root, 500, 400);
//        stage.initModality(Modality.WINDOW_MODAL);
//        stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
        stage.setScene(optionsScene);

        stage.show();

    }



    public void exit(ActionEvent actionEvent) {
        System.exit(0);
    }



}
