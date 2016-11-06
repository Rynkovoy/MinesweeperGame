package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import sample.Fields;

/**
 * Created by anton on 29.08.2016.
 */
public class Options extends Fields {


    @FXML
    public RadioButton rbBeginner;
    @FXML
    public RadioButton rbExperienced;
    @FXML
    public RadioButton rbExpert;
    @FXML
    public RadioButton rbSpecial;



    @FXML
    public TextField labelHeight;

    @FXML
    public TextField labelWidth;

    @FXML
    public TextField labelMines;

    @FXML
    public Label labelSave;



    @FXML
    public Label labelExperiencedHeight;

    @FXML
    public Label labelExperiencedWidth;

    @FXML
    public Label labelExperiencedMines;

    @FXML
    public Label labelExpertHeight;

    @FXML
    public Label labelExpertWidth;

    @FXML
    public Label labelExpertMines;

    @FXML
    public Label labelBeginnerHeight;

    @FXML
    public Label labelBeginnerMines;

    @FXML
    public Label labelBeginnerWidth;


    public void cancel(ActionEvent actionEvent) {

        stage.setScene(menuScene);
        stage.show();
    }


    public void setRbSpecial(ActionEvent actionEvent) {
        if(rbSpecial.isSelected()){
            labelHeight.setDisable(false);
            labelWidth.setDisable(false);
            labelMines.setDisable(false);
        }else{
            labelHeight.setDisable(true);
            labelWidth.setDisable(true);
            labelMines.setDisable(true);
        }
    }

    public void save(ActionEvent actionEvent) {


        if(rbSpecial.isSelected()) {
            try {
                height = Integer.parseInt(labelHeight.getText());
                width = Integer.parseInt(labelWidth.getText());
                mines = Integer.parseInt(labelMines.getText());

                if(mines > height * width){
                    labelSave.setStyle("-fx-text-fill: red; -fx-font-weight: bold");
                    labelSave.setText("Error! Mines more than cells.");
                    height = 9;
                    width = 9;
                    mines = 10;
                }else {
                    labelSave.setStyle("-fx-text-fill: green; -fx-font-weight: bold");
                    labelSave.setText("Saved!");
                }
            } catch (Exception e) {
                labelSave.setStyle("-fx-text-fill: red; -fx-font-weight: bold");
                labelSave.setText("Please, enter the fields of numbers!");

                labelHeight.setText("");
                labelWidth.setText("");
                labelMines.setText("");

                height = 9;
                width = 9;
                mines = 10;
            }
        }else if(rbBeginner.isSelected()){
            height = Integer.parseInt(labelBeginnerHeight.getText());
            width = Integer.parseInt(labelBeginnerWidth.getText());
            mines = Integer.parseInt(labelBeginnerMines.getText());
            labelSave.setStyle("-fx-text-fill: green; -fx-font-weight: bold");
            labelSave.setText("Saved!");
        }else if(rbExperienced.isSelected()){
            height = Integer.parseInt(labelExperiencedHeight.getText());
            width = Integer.parseInt(labelExperiencedWidth.getText());
            mines = Integer.parseInt(labelExperiencedMines.getText());
            labelSave.setStyle("-fx-text-fill: green; -fx-font-weight: bold");
            labelSave.setText("Saved!");
        }else if(rbExpert.isSelected()){
            height = Integer.parseInt(labelExpertHeight.getText());
            width = Integer.parseInt(labelExpertWidth.getText());
            mines = Integer.parseInt(labelExpertMines.getText());
            labelSave.setStyle("-fx-text-fill: green; -fx-font-weight: bold");
            labelSave.setText("Saved!");
        }

    }
}
