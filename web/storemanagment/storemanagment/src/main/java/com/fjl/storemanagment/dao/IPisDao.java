package com.fjl.storemanagment.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fjl.storemanagment.generic.IGenericDao;
import com.fjl.storemanagment.model.ProductInStore;
@Repository
public interface IPisDao extends IGenericDao<ProductInStore, Integer, Integer> {

	@Query(value = "SELECT * FROM products_stores WHERE idStore = :idStore ORDER BY idProduct",  nativeQuery = true)
	List<ProductInStore> getAllByIdStore(@Param("idStore")Integer idStore);
	
	@Query(value = "SELECT * FROM products_stores WHERE idProduct = :idProduct ORDER BY idProduct",  nativeQuery = true)
	List<ProductInStore> getAllByIdProduct(@Param("idProduct")Integer idProduct);
	
}
