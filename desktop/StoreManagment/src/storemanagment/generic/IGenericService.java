/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storemanagment.generic;

import java.io.Serializable;
import java.util.List;

/**
 * Interfece generica que establece contrato
 * con los servicios de consulta a BBDD.
 * 
 * @author FAQ
 */
public interface IGenericService <T, ID extends Serializable, FK>{
    void save (T enteity);
    void delete(ID id);
    T get(ID id);
    List<T> getAll();
    List<T> getAllForangeKey(FK fkey);
} 
