/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fjl.desktop.storemanagment.service;

import com.fjl.desktop.storemanagment.dao.ProductInStoreDAO;
import com.fjl.desktop.storemanagment.ddbb.Conexion;
import com.fjl.desktop.storemanagment.ddbb.IConexion;
import com.fjl.desktop.storemanagment.generic.GenericService;
import com.fjl.desktop.storemanagment.generic.IGenericDao;
import com.fjl.desktop.storemanagment.interfaces.IServicePis;
import com.fjl.desktop.storemanagment.model.ProductInStore;

/**
 *
 * @author FAQ
 */
public class PisService extends GenericService<ProductInStore,ProductInStore, Integer> implements IServicePis {

    @Override
    public IGenericDao<ProductInStore, ProductInStore, Integer> getDao() {
            IConexion conn = Conexion.getInstace();
            return new ProductInStoreDAO(conn);
    }
    
}
