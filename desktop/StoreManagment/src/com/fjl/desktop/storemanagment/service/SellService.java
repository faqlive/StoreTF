/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fjl.desktop.storemanagment.service;

import com.fjl.desktop.storemanagment.dao.SellDao;
import com.fjl.desktop.storemanagment.ddbb.Conexion;
import com.fjl.desktop.storemanagment.ddbb.IConexion;
import com.fjl.desktop.storemanagment.generic.GenericService;
import com.fjl.desktop.storemanagment.generic.IGenericDao;
import com.fjl.desktop.storemanagment.interfaces.IServiceSell;
import com.fjl.desktop.storemanagment.model.Sell;

/**
 *
 * @author FAQ
 */
public class SellService extends GenericService<Sell, Integer, Integer> implements IServiceSell {

    @Override
    public IGenericDao<Sell, Integer, Integer> getDao() {
        IConexion conn = Conexion.getInstace();
        return new SellDao(conn);
    }
    
}
