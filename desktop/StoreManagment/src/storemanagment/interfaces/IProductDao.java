/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storemanagment.interfaces;

import storemanagment.generic.IGenericDao;
import storemanagment.model.Product;

/**
 *
 * @author FAQ
 */
public interface IProductDao extends IGenericDao<Product,Integer,String> {
    
}
