/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storemanagment.interfaces;

import java.io.Serializable;
import storemanagment.generic.IGenericDao;
import storemanagment.model.ProductInStore;

/**
 *
 * @author FAQ
 */
public interface IPisDao extends IGenericDao<ProductInStore, ProductInStore, Integer> {
    
}
