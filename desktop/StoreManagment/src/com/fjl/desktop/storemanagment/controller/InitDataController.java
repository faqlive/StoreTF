/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fjl.desktop.storemanagment.controller;

import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.util.ResourceBundle;

import com.fjl.desktop.storemanagment.StoreManagment;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import com.fjl.desktop.storemanagment.util.Init;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
    @FXML
    private MenuItem aboutButton;
    @FXML
    private MenuItem closeButtom;


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
    private void close(ActionEvent event){

        //System.out.println(node.getId());
        // Desde un parametro ya inicializado del Nodo pricipal (en este caso el Pane)
        // Obtengo la ecena (Pane) y luego la ventana  (State)
        // Para lluego cerrarla
        Stage st = (Stage) this.btnAuto.getScene().getWindow();
        st.close();
    }

    @FXML
    private void click(ActionEvent event){
        Stage stPadre = (Stage) this.btnAuto.getScene().getWindow();
        try{
            FXMLLoader loader = new FXMLLoader();
            URL url = StoreManagment.class.getResource("view/AboutView.fxml");
            loader.setLocation(url);
            InitDataController controlador = loader.getController();
            //  Cargo el RootNode al
            Pane aboutWindow = (Pane) loader.load();
            // Crear Scene con root node.
            Scene scene = new Scene(aboutWindow);
            Stage st = new Stage();

            //st.initModality(Modality.APPLICATION_MODAL);
            st.setScene(scene);
            st.setOnCloseRequest(event1 -> stPadre.show());
            st.show();



            stPadre.hide();
            //st.setOnCloseRequest(event1 -> controlador.closeWindows());
        }catch (IOException e){
            System.out.println(e.getStackTrace());
        }


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

