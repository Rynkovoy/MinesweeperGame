package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import javax.swing.*;
import java.awt.*;
import java.awt.Label;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

/**
 * Created by anton on 30.08.2016.
 */
public class Board extends Fields {

    protected class CellImageView extends ImageView {
        private int row, column;

        public CellImageView(int row, int column, Image image) {
            super(image);
            this.row = row;
            this.column = column;
        }

        public int getRow() {
            return row;
        }

        public void setRow(int row) {
            this.row = row;
        }

        public int getColumn() {
            return column;
        }

        public void setColumn(int column) {
            this.column = column;
        }
    }

    public Board(){

        getOptions();
        generateBoard();
        showBoard();
        //Logic logic = new Logic();
        getProcess();
    }



    private void getOptions() {

        /*------- OPTIONS -------*/
        stage.setTitle("Minesweeper");
        stage.setResizable(false);
        layout = new GridPane();
        vBoxMain = new VBox();
        hBoxWrapper = new HBox();
        vBoxBtnWrapper = new VBox();

        labelFlagsCount = new javafx.scene.control.Label("Flags: " + flags);
        btnRestart = new Button("Restart");
        btnExit = new Button("Exit");
        btnRestart.setMinSize(60, 10);
        btnExit.setMinSize(60, 10);
        btnExit.setStyle("-fx-start-margin: inherit");

        vBoxBtnWrapper.getChildren().addAll(btnRestart, btnExit);
        vBoxBtnWrapper.setAlignment(Pos.CENTER);

        labelFlagsCount.setStyle("-fx-border-color: black");

        btnRestart.setStyle("");
        hBoxWrapper.setStyle("-fx-max-width: inherit; -fx-border-color:darkgoldenrod; -fx-background-color:whitesmoke; -fx-alignment: center;");


        hBoxWrapper.getChildren().addAll(labelFlagsCount, btnRestart, btnExit);





        for (int i = 0; i < height; i++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPercentHeight(100.0 / height);
            layout.getRowConstraints().add(rowConst);
        }
        for (int i = 0; i < width; i++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPercentWidth(100.0 / width);
            layout.getColumnConstraints().add(colConst);
        }

        hBoxWrapper.setPrefSize(24 * width, 45);
        layout.setPrefSize(24 * width, 24 * height);
        vBoxMain.getChildren().addAll(hBoxWrapper, layout);

        ingameScene = new Scene(vBoxMain);
        stage.setScene(ingameScene);

    }

    private void generateBoard(){
        /*------- generateBoard -------*/
        for (int i = 0; i < height; i++) { // Generate empty cells
            for (int j = 0; j < width; j++) {

                board[i][j] = GameObject.CELL;
            }
        }


        int rI = 0;
        int rJ = 0;
        for (int i = 0; i < mines; ) {
         // Generate mines
            rI = r.nextInt(height);
            rJ = r.nextInt(width);
            if(board[rI][rJ] == GameObject.MINE){
                continue;
            }else{
                board[rI][rJ] = GameObject.MINE;
                i++;//////// нужно исправить. +++++++
            }
        }

    }



    private void showBoard(){
        /*------- showBoard -------*/
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {

                images[i][j] = new CellImageView(i, j, cell);
                layout.add(images[i][j], j, i);
            }
        }
    }




    CellImageView view;

    public int calcMine(int i, int j) {

        int mineCount = 0;
        if (i != 0 && j != 0 &&                  board[i - 1][j - 1] == Fields.GameObject.MINE) mineCount++;
        if (i != height - 1 && j != width - 1 && board[i + 1][j + 1] == Fields.GameObject.MINE) mineCount++;
        if (i != height - 1 &&j != 0 &&          board[i + 1][j - 1] == Fields.GameObject.MINE) mineCount++;
        if (i != 0 && j != width - 1 &&          board[i - 1][j + 1] == Fields.GameObject.MINE) mineCount++;
        if (j != width - 1 &&                    board[i][j + 1] == Fields.GameObject.MINE) mineCount++;
        if (j != 0 &&                            board[i][j - 1] == Fields.GameObject.MINE) mineCount++;
        if (i != height - 1 &&                   board[i + 1][j] == Fields.GameObject.MINE) mineCount++;
        if (i != 0 &&                            board[i - 1][j] == Fields.GameObject.MINE) mineCount++;
        return mineCount;
    }



//    public void changeImg(){
//
//    }


    ArrayList<Integer> mineX = new ArrayList<>();
    ArrayList<Integer> mineY = new ArrayList<>();

    void openCell(int i, int j) throws InterruptedException {
        if (board[i][j] == GameObject.MINE) {
            layout.setStyle("-fx-background-color: red");
                        board[i][j] = Fields.GameObject.MINE_DEAD;
                        changeImg(i, j, mineDead);
                        for (int k = 0; k < height; k++) {
                            for (int l = 0; l < width; l++) {
                                if (board[k][l] == GameObject.MINE) changeImg(k, l, mine);
                            }
                        }
                        for (int k = 0; k < mineX.size(); k++) {
                            changeImg(mineY.get(k), mineX.get(k), mineFlag);
                        }
//            Thread.sleep(2000);
//            Alert alert = new Alert(Alert.AlertType.INFORMATION, "You lose!", ButtonType.FINISH);
//            alert.show();
//            Thread.sleep(2000);
//            stage.setScene(menuScene);

        } else if (board[i][j] == GameObject.CELL) { // is NORMAL, and not EMPTY!
            int result = calcMine(i, j);
            if (result == 0) {
                board[i][j] = GameObject.EMPTY;
                changeImg(i, j, openCell);
                for (int k = i - 1; k <= i + 1; k++) {
                    for (int l = j - 1; l <= j + 1; l++) {
                        if (l >= 0 && l < width && k >= 0 && k < height) {
                            openCell(k, l);
                        }
                    }
                }
            } else {
                board[i][j] = GameObject.SOME_VALUE;
                    switch (result){
                        case 0: changeImg(i, j, cell0); break;
                        case 1: changeImg(i, j, cell1); break;
                        case 2: changeImg(i, j, cell2); break;
                        case 3: changeImg(i, j, cell3); break;
                        case 4: changeImg(i, j, cell4); break;
                        case 5: changeImg(i, j, cell5); break;
                        case 6: changeImg(i, j, cell6); break;
                        case 7: changeImg(i, j, cell7); break;
                        case 8: changeImg(i, j, cell8); break;
                    }
            }
        }

    }





    /*------- Process -------*/
    public void getProcess(){



        EventHandler<MouseEvent> handler = new EventHandler<MouseEvent>() { // out
            @Override
            public void handle(MouseEvent event) {
                view = (CellImageView) event.getTarget();

                int row = view.getRow();
                int coll = view.getColumn();

                if(event.getButton().equals(MouseButton.PRIMARY)){
                    if (board[row][coll] == GameObject.CELL) {
                        System.out.println("It's CELL");
                    }
                    try {
                        openCell(row, coll);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else if(event.getButton().equals(MouseButton.SECONDARY)) {
                    //setFlag(view.getRow(), view.getColumn());

                    if (board[row][coll] == GameObject.CELL) {
                        if(flags > 0) {
                            labelFlagsCount.setText(--flags + "");
                            changeImg(row, coll, flag);
                            //if (  board[row][coll] == GameObject.FLAG) System.out.println("SET FLAG from cell");
                            images[row][coll].setOnMouseClicked(this);
                            board[row][coll] = GameObject.FLAG;
                            if (board[row][coll] == GameObject.FLAG) {
                                System.out.println("SET FLAG from CELL");
                            }
                        }
                    } else if (board[row][coll] == GameObject.MINE) {
                        if(flags > 0) {
                            labelFlagsCount.setText(--flags + "");
                            mineY.add(row);
                            mineX.add(coll);
                            System.out.println(row + " : " + coll);
                            changeImg(row, coll, flag);
                            //if (  board[row][coll] == GameObject.FLAG) System.out.println("SET FLAG from mine");
                            images[row][coll].setOnMouseClicked(this);
                            board[row][coll] = GameObject.FLAG;
                            if (board[row][coll] == GameObject.FLAG) {
                                System.out.println("SET FLAG from MINE");
                            }
                        }
                    } else if (board[row][coll] == GameObject.FLAG) {
                        labelFlagsCount.setText(++flags + "");
                        System.out.println("SET NOT_SURE from FLAG");
                        changeImg(row, coll, not_sure);
                        images[row][coll].setOnMouseClicked(this);
                        board[row][coll] = GameObject.NOT_SURE;
                    } else if(board[row][coll] == GameObject.NOT_SURE) {
                        changeImg(row, coll, cell);
                        board[row][coll] = GameObject.CELL;
                        images[row][coll].setOnMouseClicked(this);
                        for (int i = 0; i < mineY.size(); i++) {
                                if (mineY.get(i) == row && mineX.get(i) == coll) {
                                    images[row][coll].setOnMouseClicked(this);
                                    board[row][coll] = GameObject.MINE;
                                    System.out.println(row + " : " + coll + " = MINE!!!" );
                                }
                        }
                    }



//                    if (board[view.getRow()][ view.getColumn()] == GameObject.CELL) {
//                        changeImg(view.getRow(), view.getColumn(), flag);
//                    }
                }

//                boolean isWin = false;
//                m:
//                for (int i = 0; i < width; i++) {
//                    for (int j = 0; j < height; j++) {
//                        if(board[i][j] != GameObject.MINE) {
//                            isWin = true;
//                        }else {
//                            isWin = false;
//                            break m;
//                        }
//                    }
//                }
//                if(isWin) {
//                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "You win!", ButtonType.FINISH);
//                    alert.show();
//                    try {
//                        Thread.sleep(2000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    stage.setScene(menuScene);
//                }
            }
        };

        for (int i = 0; i < height; i++) { //action mouse on each cell
            for (int j = 0; j < width; j++) {
                images[i][j].setOnMouseClicked(handler);
            }
        }

        btnRestart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                generateBoard();
                showBoard();
                layout.setStyle("-fx-background-color: aliceblue");
                for (int i = 0; i < height; i++) { //action mouse on each cell
                    for (int j = 0; j < width; j++) {
                        images[i][j].setOnMouseClicked(handler);
                    }
                }
                flags = mines;
                labelFlagsCount.setText(String.valueOf(flags));

            }
        });

        btnExit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(menuScene);
            }
        });

    }

    void changeImg(int i, int j, Image image){

            images[i][j] = new CellImageView(i, j, image);
            layout.add(images[i][j], j, i);
    }


}



































