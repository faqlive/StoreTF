package com.fjl.storemanagment.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fjl.storemanagment.dto.TotalSales;
import com.fjl.storemanagment.generic.IGenericDao;
import com.fjl.storemanagment.model.Sell;

@Repository
public interface ISellDao extends IGenericDao<Sell, Integer, Integer> {
	
	@Query(value = "SELECT * FROM sales s WHERE s.year BETWEEN :since AND :until ORDER BY s.year",
			countQuery ="SELECT count(*) FROM sales s WHERE s.year BETWEEN :since AND :until",
			nativeQuery = true)
    public Page<Sell> sellBetweenDate(@Param("since") Date dateSince,
    									@Param("until") Date dateUntil, Pageable paging);
	
	@Query(value = "SELECT * FROM sales s WHERE s.year BETWEEN :since AND :until AND s.idStore = :idStore ORDER BY s.year",
			countQuery ="SELECT count(*) FROM sales s WHERE s.year BETWEEN :since AND :until AND s.idStore = :idStore",
			nativeQuery = true)
    public Page<Sell> sellStoreBetweenDate(@Param("since") Date dateSince,
    										@Param("until") Date dateUntil,
    										@Param("idStore") Integer idStore,
    										Pageable paging);
	
	@Query(value = "SELECT  "
			+ "sa.idProduct AS \"idProduct\", pr.nameProduct AS \"nameProduct\", count(sa.idProduct) AS \"stock\", sum(pr.priceProduct) AS \"totalAmount\""
			+ "FROM  sales sa INNER JOIN products pr ON sa.idProduct = pr.idProduct GROUP BY sa.idProduct",
			nativeQuery = true)
    public List<TotalSales> totalSells();
	
	@Query(value = "SELECT  "
			+ "sa.idProduct AS \"idProduct\", pr.nameProduct AS \"nameProduct\", count(sa.idProduct) AS \"stock\", sum(pr.priceProduct) AS \"totalAmount\""
			+ "FROM  sales sa INNER JOIN products pr ON sa.idProduct = pr.idProduct  and sa.idStore = :idStore GROUP BY sa.idProduct",
			nativeQuery = true)
    public List<TotalSales> storeSells(@Param("idStore") Integer idStore);
	
	@Query(value = "SELECT  "
			+ "sa.idProduct AS \"idProduct\", pr.nameProduct AS \"nameProduct\", count(sa.idProduct) AS \"stock\", sum(pr.priceProduct) AS \"totalAmount\""
			+ "FROM  sales sa INNER JOIN products pr ON sa.idProduct = pr.idProduct and YEAR(sa.year) = :year GROUP BY sa.idProduct",
			nativeQuery = true)
    public List<TotalSales> annualSells(@Param("year") Integer year);
	
	@Query(value = "SELECT  "
			+ "sa.idProduct AS \"idProduct\", pr.nameProduct AS \"nameProduct\", count(sa.idProduct) AS \"stock\", sum(pr.priceProduct) AS \"totalAmount\""
			+ "FROM sales sa INNER JOIN  products pr WHERE pr.idProduct = sa.idProduct GROUP BY sa.idProduct "
			+ "ORDER BY stock DESC LIMIT 0, 10",
			nativeQuery = true)
    public List<TotalSales> topSell();
	

	
	
}