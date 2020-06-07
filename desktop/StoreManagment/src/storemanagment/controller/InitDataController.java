/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storemanagment.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clck(ActionEvent event) {
        new Init().initialization();
    }

    @FXML
    private void autoGeneret(ActionEvent event) {
        System.out.println("Generando bases de datos.");
        new Init().initialization();
    }
    
}
