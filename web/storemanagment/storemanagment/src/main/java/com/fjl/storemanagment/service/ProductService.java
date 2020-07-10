package com.fjl.storemanagment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fjl.storemanagment.dao.IProductDao;
import com.fjl.storemanagment.generic.GenericService;
import com.fjl.storemanagment.generic.IGenericDao;
import com.fjl.storemanagment.interfaces.IProductService;
import com.fjl.storemanagment.model.Product;


@Service
public class ProductService extends GenericService<Product,Integer, String> implements IProductService {

	@Autowired
	private IProductDao productDao;
	
	@Override
	public IGenericDao<Product, Integer, String> getDao() {
		return productDao;
	}

	@Override
	public Page<Product> getAllForangeKey(String forangeKey,Pageable paging) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getAllForangeKey(String forangeKey) {
		// TODO Auto-generated method stub
		return null;
	}


}
