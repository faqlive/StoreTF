package com.fjl.storemanagment.dao;


import java.util.Optional;

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
	
	
	/**
	 * Actualiza el valor del stock de una dereminada entrada
	 * PisID
	 * 
	 * 
	 * */
	@Modifying(flushAutomatically = true, clearAutomatically = true)
	@Transactional
	@Query(value ="UPDATE products_stores SET stock = :stock WHERE idProduct = :idProduct AND idStore =:idStore",  nativeQuery = true)
	void update(@Param("idStore")Integer idStore, @Param("idProduct")Integer idProduct, @Param("stock") Integer stock);
	
	/**
	 * Devuelve lista de todos los productos faltantes o 
	 * en riesgo de rotura de Stock de determinado almacén
	 * 
	 * */

	@Query(value = "SELECT c.idProduct AS \"idProduct\", c.idStore AS \"idStore\", c.stock AS \"stock\" "
			+ " FROM (SELECT A.idProduct , A.idStore , IFNULL(B.stock, 0) AS stock " + 
	"FROM (SELECT pr.idProduct, sh.idStore " + 
	"FROM products pr, storehouse sh ) A " + 
	"LEFT JOIN products_stores B USING(idProduct,idStore) " + 
	"WHERE  B.stock is null or B.stock <= :riskStock) C " + 
	"WHERE c.idStore = :idStore ORDER BY stock DESC, c.idProduct", 
	countQuery ="SELECT count(*) FROM (SELECT A.idProduct , A.idStore , IFNULL(B.stock, 0) AS stock "  + 
			"FROM (SELECT pr.idProduct, sh.idStore "  + 
			"FROM products pr, storehouse sh ) A " + 
			"LEFT JOIN products_stores B USING(idProduct,idStore) "  + 
			"WHERE  B.stock is null or B.stock <= :riskStock) C " + 
			"WHERE c.idStore = :idStore ORDER BY stock DESC, c.idProduct",nativeQuery = true)
	Page<ProductInStore> ceckRiskStockByStore(@Param("idStore")Integer idStore,@Param("riskStock") Integer riskStock, Pageable paging);
	
	/**
	 * Cuenta la cantidad de productos sin stock
	 * en determinado almacen
	 * 
	 * @param Integer idStore;
	 * 
	 * */

	@Query(value="SELECT count(*) FROM (SELECT A.idProduct, A.idStore, IFNULL(B.stock, 0) AS stock " + 
			"FROM (SELECT pr.idProduct, sh.idStore " + 
			"FROM products AS pr, storehouse AS sh) A " + 
			"LEFT JOIN products_stores B USING (idProduct, idStore)" + 
			"WHERE B.stock is null ) C " + 
			"WHERE c.idStore = :idStore ORDER BY c.stock DESC", 
			nativeQuery = true)
	Integer countBreakStockByStore(@Param("idStore")Integer idStore);
	
	/**
	 * Cuenta la cantidad de productos con
	 * riego de ruptura en determinada tienda.
	 * 
	 * @param Integer riskStock;
	 * @param Integer riskStock;
	 * 
	 * */

	@Query(value="SELECT count(*) FROM(SELECT A.idProduct, A.idStore, IFNULL(B.stock, 0) AS stock " + 
			"FROM (SELECT pr.idProduct, sh.idStore " + 
			"FROM products AS pr, storehouse AS sh) A " + 
			"LEFT JOIN products_stores B USING (idProduct, idStore) " + 
			"WHERE B.stock <= (CASE WHEN :riskStock IS NULL THEN 5 ELSE :riskStock END)) C " + 
			"WHERE idStore = :idStore ORDER BY stock DESC", nativeQuery = true)
	Integer countRiskStockByStore(@Param("idStore")Integer idStore, @Param("riskStock")Optional<Integer> riskStock);
	
	
}
