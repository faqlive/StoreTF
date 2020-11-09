/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fjl.desktop.storemanagment.ddbb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author FAQ
 */
public class OracleConexion {
    public static Connection getConexion() throws SQLException {
        Connection conn = null;
         try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhot:1521:SID","USER","PASS");
        } catch (SQLException | ClassNotFoundException ex) {
            throw new SQLException();
        }
        return conn;
    }
}
