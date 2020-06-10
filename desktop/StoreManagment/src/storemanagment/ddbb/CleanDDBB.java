/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storemanagment.ddbb;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author FAQ
 */
public class CleanDDBB {
    protected Connection conexion;
    private Statement statement;
    
    private static final String SQL_const_off = "SET FOREIGN_KEY_CHECKS=0";
    private static final String SQL_const_on = "SET FOREIGN_KEY_CHECKS=1";
    private static final String SQL_trunc = "TRUNCATE ";
    private static final String[] tables = {"products", "products_stores","sales","storehouse","location"};

    public CleanDDBB() {
            super();
            try {
                IConexion conexion = Conexion.getInstace();
                this.conexion = conexion.conectBBDD();
            } catch (SQLException e) {
                    e.printStackTrace();
            }
    }
    
    public void cleanAll(){
        
        try {
            statement = conexion.createStatement(); 
            statement.addBatch(SQL_const_off);
            for(String tb : tables){
            statement.addBatch(SQL_trunc.concat(tb));
            }
            statement.addBatch(SQL_const_on);
            statement.executeBatch();
        } catch (SQLException ex) {
            System.err.format("SQL State: %s\n%s", ex.getSQLState(), ex.getMessage());
        }finally{
            try {
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(CleanDDBB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
