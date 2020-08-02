/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storemanagment.service;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import storemanagment.dao.SellDao;
import storemanagment.ddbb.Conexion;
import storemanagment.ddbb.IConexion;
import storemanagment.generic.GenericService;
import storemanagment.generic.IGenericDao;
import storemanagment.hand.ExceptionNoDB;
import storemanagment.interfaces.IServiceSell;
import storemanagment.model.Sell;

/**
 *
 * @author FAQ
 */
public class SellService extends GenericService<Sell, Integer, Integer> implements IServiceSell{

    @Override
    public IGenericDao<Sell, Integer, Integer> getDao() {
        IConexion conn = Conexion.getInstace();
        return new SellDao(conn);
    }
    
}
