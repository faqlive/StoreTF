/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fjl.desktop.storemanagment.controller;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Clase publica <h4>ErrorAlert</h4>
 *
 * Calse destina a mostar mensajes de error
 * al no encontrar los recursos correctos de BBDD
 *
 * @author Facundo J. López
 * @version 1.0
 * @since 12/2020
 *
 */
public class ErrorAlert {

    /**
     * Lanza la alerta con los siguientes mensajes.
     *
     *
     * <h6>"ERROR"</h6>
     * <p>"Ha ocurrido un error al intetar acceder a la base de datos
     *  Por favor verifique que el servicio del motor de Base de Datos este activo y la
     *  Base de datos Correctamente creada."</p>
     *
     *
     * */
    public static void errorAlert(){
        Alert alert = new Alert (AlertType.WARNING);
        alert.setTitle("ERROR");
        alert.setHeaderText("Ha ocurrido un error al intetar acceder a la base de datos");
        alert.setContentText("Por favor verifique que el servicio del motor de Base de Datos este activo y la " 
        +"Base de datos Correctamente creada.");
        alert.showAndWait();
    }
 

        

}
