/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storemanagment.service;

import java.util.logging.Level;
import java.util.logging.Logger;
import storemanagment.dao.StoreHomeDao;
import storemanagment.ddbb.Conexion;
import storemanagment.ddbb.IConexion;
import storemanagment.generic.GenericService;
import storemanagment.generic.IGenericDao;
import storemanagment.hand.ExceptionNoDB;
import storemanagment.interfaces.IServiceStoreHome;
import storemanagment.model.StoreHome;

/**
 *
 * @author FAQ
 */
public class StoreHomeService extends GenericService <StoreHome,Integer, Integer> implements IServiceStoreHome {

    @Override
    public IGenericDao<StoreHome, Integer, Integer> getDao() {
        IConexion conn = Conexion.getInstace();
        return new StoreHomeDao(conn);
    }

}
