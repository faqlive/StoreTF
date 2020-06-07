/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storemanagment.service;

import java.io.Serializable;
import storemanagment.dao.ProductInStoreDAO;
import storemanagment.ddbb.Conexion;
import storemanagment.ddbb.IConexion;
import storemanagment.generic.GenericService;
import storemanagment.generic.IGenericDao;
import storemanagment.interfaces.IServicePis;
import storemanagment.model.ProductInStore;

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
