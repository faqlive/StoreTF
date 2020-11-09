/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fjl.desktop.storemanagment.generic;

import java.sql.Connection;
import java.sql.SQLException;
import com.fjl.desktop.storemanagment.ddbb.IConexion;

/**
 *
 * @author FAQ
 */
public class GenericDao {
   
    protected Connection conexion;
    
    public GenericDao(IConexion conexion){
            try {
                this.conexion = conexion.conectBBDD();
            } catch (SQLException e) {
                
            }
    }   
}
