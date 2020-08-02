/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storemanagment.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.paint.Paint;
import storemanagment.hand.ExceptionNoDB;
import storemanagment.util.Init;

/**
 * FXML Controller class
 *
 * @author FAQ
 */
public class InitDataController implements Initializable {
    @FXML
    private Button btnStore;
    @FXML
    private Button btnProducts;
    @FXML
    private Button btnSells;
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
        progBar.setProgress(0);
        progTxt.setVisible(false);
    }    
    
    

    @FXML
    private void clck(ActionEvent event) {
    }

    @FXML
    private void autoGeneret(ActionEvent event) {
        
        btnAuto.setDisable(true);
        progBar.indeterminateProperty();
        
        progTxt.setVisible(true);
        progBar.setVisible(true);
        progBar.indeterminateProperty().addListener(new ChangeListener<Boolean>(){
            @Override
            public void changed(ObservableValue<? extends Boolean> ob, Boolean v1, Boolean v2) {
                if(v2){
                   progTxt.setText("En progreso");
                   progTxt.setTextFill(Paint.valueOf("BLACK"));
                }else if(v1){
                   progTxt.setText("Carga EXITOSA");
                   progTxt.setTextFill(Paint.valueOf("GREEN"));
                   btnAuto.setDisable(false);
                   progBar.setVisible(false);
                }else{
                   progTxt.setText("ERROR EN CARGA");
                   progTxt.setTextFill(Paint.valueOf("RED"));
                   btnAuto.setDisable(false);
                   progBar.setVisible(false);
                   ErrorAlert.errorAlert(); 
                }
            }
        });
               
        Task task;
        task = new Task() {
            
            @Override
            protected Object call() throws Exception {
                try {
                    new Init().initialization();
                    updateProgress(1, 1);
                    return true;
                } catch (ExceptionNoDB ex) {
                    
                    return false;
//                    ErrorAlert.errorAlert();             
                }finally{
                    this.succeeded();
                }
              //  return null;
            }
        };
                
        progBar.progressProperty().unbind();
        progBar.progressProperty().bind(task.progressProperty());   
   
        Thread th = new Thread(task);
        th.start();
    }
}

class Progress implements Runnable{

    @Override
    public void run() {
     try {
            new Init().initialization();
        } catch (ExceptionNoDB ex) {
            ErrorAlert.errorAlert();
        }
    }

}



