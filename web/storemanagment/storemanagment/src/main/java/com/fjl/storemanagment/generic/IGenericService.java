package com.fjl.storemanagment.generic;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fjl.storemanagment.model.ProductInStore;
/**
 * CONTRATO PARA TODAS LAS API
 * */
public interface IGenericService <T, ID extends Serializable, FK>{
    void save (T entity);
    void delete(ID id);
    T get(ID id);
    Page<T> getAll(Pageable paging);
    List<T> getAll();
    Page<T> getAllForangeKey(FK forangeKey,Pageable paging);
    List<T> getAllForangeKey(FK forangeKey);


} 
