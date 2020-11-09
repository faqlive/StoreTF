/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fjl.desktop.storemanagment.service;

import com.fjl.desktop.storemanagment.interfaces.IServiceProduct;
import com.fjl.desktop.storemanagment.dao.ProductDao;
import com.fjl.desktop.storemanagment.ddbb.Conexion;
import com.fjl.desktop.storemanagment.ddbb.IConexion;
import com.fjl.desktop.storemanagment.generic.GenericService;
import com.fjl.desktop.storemanagment.generic.IGenericDao;
import com.fjl.desktop.storemanagment.model.Product;

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
