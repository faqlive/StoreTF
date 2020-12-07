/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fjl.desktop.storemanagment.controller;

import java.io.StringReader;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.paint.Paint;
import com.fjl.desktop.storemanagment.util.Init;

import static java.lang.Thread.sleep;

/**
 * FXML Controller class
 *
 * @author FAQ
 */
public class InitDataController implements Initializable {


    @FXML
    private Button btnAuto;
    @FXML
    private ProgressBar progBar;
    @FXML
    private Label progTxt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        progBar.setVisible(false);
        progTxt.setVisible(false);
    }    
    
    

    @FXML
    private void clck(ActionEvent event) {
    }

    @FXML
    private void autoGeneret(ActionEvent event) {
        progBar.progressProperty().unbind();
        btnAuto.setDisable(true);
        progBar.setProgress(0);
        progTxt.setText("");
        progTxt.setTextFill(Paint.valueOf("BLACK"));
        progTxt.setVisible(true);
        progBar.setVisible(true);

        Task task = null;
// Se genera una constante para poder utilizarlo dentro de la funcion lamda


        try {
            task = Init.initialization();
            task.setOnFailed(event1 -> {
                ErrorAlert.errorAlert();
                btnAuto.setDisable(false);
                progBar.setVisible(false);
                progTxt.setVisible(false);
                });
        } catch (Throwable throwable) {
            ErrorAlert.errorAlert();
            throwable.printStackTrace();

            return;
        }

        progBar.progressProperty().bind(task.progressProperty());
        Task finalTask = task;
        progBar.progressProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue.doubleValue() == 1) {
                progTxt.setText("Carga EXITOSA");
                progTxt.setTextFill(Paint.valueOf("GREEN"));
                btnAuto.setDisable(false);
            } else {
                progTxt.setText(finalTask.getMessage());
            }
        });


        Thread th = new Thread(task);


        th.start();
    }
}

