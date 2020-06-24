package com.fjl.storemanagment.generic;

import java.io.Serializable;
import java.util.List;
/**
 * CONTRATO PARA TODAS LAS API
 * */
public interface IGenericService <T, ID extends Serializable, FK>{
    void save (T enteity);
    void delete(ID id);
    T get(ID id);
    List<T> getAll();
    public List<T> getAllForangeKey(FK forangeKey);

} 
