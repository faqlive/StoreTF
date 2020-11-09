/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fjl.desktop.storemanagment.service;

import com.fjl.desktop.storemanagment.dao.StoreHomeDao;
import com.fjl.desktop.storemanagment.ddbb.Conexion;
import com.fjl.desktop.storemanagment.ddbb.IConexion;
import com.fjl.desktop.storemanagment.generic.GenericService;
import com.fjl.desktop.storemanagment.generic.IGenericDao;
import com.fjl.desktop.storemanagment.interfaces.IServiceStoreHome;
import com.fjl.desktop.storemanagment.model.StoreHome;

/**
 *
 * @author FAQ
 */
public class StoreHomeService extends GenericService<StoreHome,Integer, Integer> implements IServiceStoreHome {

    @Override
    public IGenericDao<StoreHome, Integer, Integer> getDao() {
        IConexion conn = Conexion.getInstace();
        return new StoreHomeDao(conn);
    }

}
