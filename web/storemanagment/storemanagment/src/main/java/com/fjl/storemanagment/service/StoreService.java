package com.fjl.storemanagment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fjl.storemanagment.dao.IStoreDao;
import com.fjl.storemanagment.generic.GenericService;
import com.fjl.storemanagment.generic.IGenericDao;
import com.fjl.storemanagment.interfaces.IStoreService;
import com.fjl.storemanagment.model.StoreHome;


@Service
public class StoreService extends GenericService<StoreHome,Integer,Integer> implements IStoreService {

	@Autowired
	private IStoreDao storeDao;
	
	@Override
	public IGenericDao<StoreHome,Integer,Integer> getDao() {
		return storeDao;
	}

	@Deprecated
	@Override
	public Page<StoreHome> getAllForangeKey(Integer forangeKey,Pageable paging) {
		// TODO Auto-generated method stub
		return null;
	}

	@Deprecated
	@Override
	public List<StoreHome> getAllForangeKey(Integer forangeKey) {
		// TODO Auto-generated method stub
		return null;
	}

}
