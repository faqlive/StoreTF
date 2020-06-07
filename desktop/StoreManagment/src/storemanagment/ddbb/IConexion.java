/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storemanagment.ddbb;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author FAQ
 */
public interface IConexion {
	public  Connection conectBBDD() throws SQLException;
	public  void disconBBDD();

}