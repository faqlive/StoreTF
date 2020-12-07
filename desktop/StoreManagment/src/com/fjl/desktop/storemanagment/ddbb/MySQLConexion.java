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
public class MySQLConexion {
    public static Connection getConexion() throws SQLException {
        Connection conn = null;
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/store?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC"
                                                ,"admin_store"
                                                ,"TFcice123");
        } catch (SQLException ex) {
            throw new SQLException();
        }
        return conn;
    }
}
