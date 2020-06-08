/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storemanagment.ddbb;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
    private PreparedStatement preStatement;
    private Statement statement;
    
    private static final String SQL_const_off = "SET FOREIGN_KEY_CHECKS=0";
    private static final String SQL_const_on = "SET FOREIGN_KEY_CHECKS=1";
    private static final String SQL_trunc = "TRUNCATE ?";
    private static final String[] tables = {"store.products", "products_stores","sales","storehouse",};

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
            // statement = conexion.createStatement(SQL_const_off);
            statement = conexion.createStatement();
           /* boolean off = statement.execute(SQL_const_off);
            statement.close();
            System.out.println(off);
            
            for(String table :tables){
                preStatement = conexion.prepareStatement(SQL_trunc);
                preStatement.setString(1, table);
                System.out.println(preStatement);
                preStatement.execute();
                preStatement.close();
            }         
            
            statement = conexion.createStatement();
            statement.execute(SQL_const_on);
            statement.close();
                   */
            statement.addBatch("SET FOREIGN_KEY_CHECKS = 0");
            statement.addBatch("TRUNCATE products");
            statement.addBatch("TRUNCATE products_stores");
            statement.addBatch("TRUNCATE sales");
            statement.addBatch("TRUNCATE storehouse");
            statement.addBatch("TRUNCATE location");
            statement.addBatch("SET FOREIGN_KEY_CHECKS = 1");
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
