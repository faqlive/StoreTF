package com.fjl.storemanagment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fjl.storemanagment.dao.ISellDao;
import com.fjl.storemanagment.generic.GenericService;
import com.fjl.storemanagment.generic.IGenericDao;
import com.fjl.storemanagment.interfaces.ISellService;
import com.fjl.storemanagment.model.Sell;

@Service
public class SellService extends GenericService<Sell,Integer,Integer> implements ISellService {
	
	@Autowired
	private ISellDao sellDao;
	
	@Override
	public IGenericDao<Sell, Integer,Integer> getDao() {
		return sellDao;
	}

	@Override
	public List<Sell> getAllForangeKey(Integer forangeKey) {
		// TODO Auto-generated method stub
		return null;
	}

}
