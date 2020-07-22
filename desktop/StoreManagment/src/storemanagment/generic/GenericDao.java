/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storemanagment.generic;

import java.sql.Connection;
import java.sql.SQLException;
import storemanagment.ddbb.IConexion;
import storemanagment.hand.ExceptionNoDB;

/**
 *
 * @author FAQ
 */
public class GenericDao {
    protected Connection conexion;
	public GenericDao(IConexion conexion) throws ExceptionNoDB {
		super();
		try {
			this.conexion = conexion.conectBBDD();
		} catch (SQLException e) {
			e.printStackTrace();
                        throw new ExceptionNoDB();
		}
	}   
}
