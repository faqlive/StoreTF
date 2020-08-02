/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storemanagment.service;

import java.util.logging.Level;
import java.util.logging.Logger;
import storemanagment.interfaces.IServiceProduct;
import storemanagment.dao.ProductDao;
import storemanagment.ddbb.Conexion;
import storemanagment.ddbb.IConexion;
import storemanagment.generic.GenericService;
import storemanagment.generic.IGenericDao;
import storemanagment.hand.ExceptionNoDB;
import storemanagment.model.Product;

/**
 *
 * @author FAQ
 */
public class ProductService extends GenericService <Product, Integer, String> implements IServiceProduct{

    @Override
    public IGenericDao<Product, Integer, String> getDao() {
            IConexion conn = Conexion.getInstace(); 
            return new ProductDao(conn);
    }
    
}
