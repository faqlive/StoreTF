package com.fjl.storemanagment.dao;

import java.sql.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

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
	
}