package sample;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Random;

/**
 * Created by anton on 30.08.2016.
 */
public abstract class Fields {

    protected static Stage stage;

    protected static  Scene menuScene;
    protected static Scene optionsScene;
    protected static Scene ingameScene;

    protected static int height = 9;
    protected static int width = 9;
    protected static int mines = 10;
    protected static int flags = mines;

    Board.CellImageView view;



    protected GridPane layout = new GridPane();

    protected GameObject[][] board = new GameObject[height][width];
    protected ImageView[][] images = new ImageView[height][width];

    protected Image cell = new Image("/img/cell.png");
    protected Image mine = new Image("/img/mine.png");
    protected Image flag = new Image("/img/flag.png");

    protected  Image mineDead = new Image("/img/mineDead.png");
    protected  Image mineFlag = new Image("/img/mineFlag.png");

    protected  Image openCell = new Image("/img/openCell.png");

    protected Image cell0 = new Image("/img/cell0.png");
    protected Image cell1 = new Image("/img/cell1.png");
    protected Image cell2 = new Image("/img/cell2.png");
    protected Image cell3 = new Image("/img/cell3.png");
    protected Image cell4 = new Image("/img/cell4.png");
    protected Image cell5 = new Image("/img/cell5.png");
    protected Image cell6 = new Image("/img/cell6.png");
    protected Image cell7 = new Image("/img/cell7.png");
    protected Image cell8 = new Image("/img/cell8.png");

    protected Image not_sure = new Image("/img/not_sure.png");



    protected Random r = new Random();

    protected enum GameObject{
        CELL, MINE, MINE_DEAD, FLAG, EMPTY, SOME_VALUE, NOT_SURE
    }

    protected VBox  vBoxMain = new VBox();
    protected VBox vBoxBtnWrapper = new VBox();
    protected VBox vBoxSm;
    protected HBox hBoxWrapper = new HBox();



    protected Label lT;
    protected Label lS;

    protected javafx.scene.control.Label labelFlagsCount;
    protected javafx.scene.control.Label labelTimer;
    protected javafx.scene.control.Button btnRestart;
    protected javafx.scene.control.Button btnExit;




}
