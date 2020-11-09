/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fjl.desktop.storemanagment.generic;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author FAQ
 */
public interface IGenericDao <T, ID extends Serializable, FK>{
    void save (T entity);
    void delete(ID id);
    T get(ID id);
    List<T> getAll();
    List<T> getAllForangeKey(FK fkey);
}
