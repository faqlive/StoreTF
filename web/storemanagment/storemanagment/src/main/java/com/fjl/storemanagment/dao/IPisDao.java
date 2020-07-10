package com.fjl.storemanagment.dao;


import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.fjl.storemanagment.generic.IGenericDao;
import com.fjl.storemanagment.model.PisID;
import com.fjl.storemanagment.model.ProductInStore;

@EnableTransactionManagement
@Repository
public interface IPisDao extends IGenericDao<ProductInStore, PisID, Integer> {

	@Query(value = "SELECT * FROM products_stores WHERE idStore = :idStore ORDER BY idProduct",  nativeQuery = true)
	Page<ProductInStore> getAllByIdStore(@Param("idStore")Integer idStore, Pageable paging);
	
	@Query(value = "SELECT * FROM products_stores WHERE idProduct = :idProduct ORDER BY idProduct",  nativeQuery = true)
	Page<ProductInStore> getAllByIdProduct(@Param("idProduct")Integer idProduct, Pageable paging);
	
	@Modifying(flushAutomatically = true, clearAutomatically = true)
	@Transactional
	@Query(value ="UPDATE products_stores SET stock = :stock WHERE idProduct = :idProduct AND idStore =:idStore",  nativeQuery = true)
	void update(@Param("idStore")Integer idStore, @Param("idProduct")Integer idProduct, @Param("stock") Integer stock);
	
}
