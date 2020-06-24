package com.fjl.storemanagment.dao;

import org.springframework.stereotype.Repository;

import com.fjl.storemanagment.generic.IGenericDao;
import com.fjl.storemanagment.model.StoreHome;

@Repository
public interface IStoreDao extends IGenericDao<StoreHome, Integer, Integer>{
	/**
	 * Interfaces de paso Repository
	 * */
}
