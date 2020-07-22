package com.fjl.storemanagment.dao;

import org.springframework.stereotype.Repository;

import com.fjl.storemanagment.generic.IGenericDao;
import com.fjl.storemanagment.model.Product;

@Repository
public interface IProductDao extends IGenericDao<Product, Integer, String>{
	

}
