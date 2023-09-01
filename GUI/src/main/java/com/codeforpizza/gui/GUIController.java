package com.codeforpizza.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class GUIController {
    public Button saveB;
    public Button viewB;
    public TextField titleF;
    public TextField yearF;
    public Label infoL;


    @FXML
    protected void onSaveButtonClick() {


        infoL.setText("Movie saved!");
    }
}