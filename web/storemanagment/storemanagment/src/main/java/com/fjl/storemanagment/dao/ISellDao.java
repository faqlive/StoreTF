package com.fjl.storemanagment.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fjl.storemanagment.generic.IGenericDao;
import com.fjl.storemanagment.model.Sell;

@Repository
public interface ISellDao extends IGenericDao<Sell, Integer, Integer> {
/*	
	@Query(value="FROM sales WHERE year BETWEEN :desde AND :hasta")
    public List<Sell> ventas_between_fechas(@Param("desde") String fecha_desde,@Param("hasta") String fecha_hasta);
*/

}
