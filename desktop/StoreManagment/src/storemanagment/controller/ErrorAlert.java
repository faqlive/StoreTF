/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storemanagment.controller;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author FAQ
 */
public class ErrorAlert {
    
    public static void errorAlert(){
        Alert alert = new Alert (AlertType.WARNING);
        alert.setTitle("ERROR");
        alert.setHeaderText("Ha ocurrido un error al intetar acceder a la base de datos");
        alert.setContentText("Por favor verifique que el servicio del motor de Base de Datos este activo y la " 
        +"Base de datos Correctamente creada.");
        alert.showAndWait();
    }
 

        

}
