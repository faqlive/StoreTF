package com.fjl.storemanagment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fjl.storemanagment.dao.IPisDao;
import com.fjl.storemanagment.generic.GenericService;
import com.fjl.storemanagment.generic.IGenericDao;
import com.fjl.storemanagment.interfaces.IPisService;
import com.fjl.storemanagment.model.ProductInStore;
@Service
public class PisService extends GenericService<ProductInStore,Integer,Integer> implements IPisService {
	
	
	@Autowired
	private IPisDao pisDao;
	
	@Override
	public IGenericDao<ProductInStore, Integer,Integer> getDao() {
		return pisDao;
	}

	@Override
	public List<ProductInStore> getAllForangeKey(Integer forangeKey) {
		// PENSAR EN COMO DIFERENCIAR ID PRODUCTO DE ID STORE.
		// pisDao.getAllByIdProduct(forangeKey);
		return pisDao.getAllByIdStore(forangeKey);
	}
	
}
