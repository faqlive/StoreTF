package com.fjl.storemanagment.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fjl.storemanagment.dao.ISellDao;
import com.fjl.storemanagment.generic.GenericService;
import com.fjl.storemanagment.generic.IGenericDao;
import com.fjl.storemanagment.interfaces.ISellService;
import com.fjl.storemanagment.model.Product;
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
	public Page<Sell> getAllForangeKey(Integer forangeKey, Pageable paging) {	
		return sellDao.findAll(paging);
	}
	
	public Page<Sell> getAllBetween(LocalDate dateSicen, LocalDate dateUntil, Pageable paging) {		
		return sellDao.sellBetweenDate(java.sql.Date.valueOf(dateSicen),
										java.sql.Date.valueOf(dateUntil),
										paging);
	}
	
	public Page<Sell> getInStoreAllBetween(LocalDate dateSicen, LocalDate dateUntil ,
											Integer idStore, Pageable paging) {
		return sellDao.sellStoreBetweenDate(java.sql.Date.valueOf(dateSicen),
											java.sql.Date.valueOf(dateUntil),
											idStore, paging);
		 
	}

	@Override
	public List<Sell> getAllForangeKey(Integer forangeKey) {
		
		return null;
	}

	@Override
	public List<Sell> totalSells() {
		
		return sellDao.totalSells();
	}

	@Override
	public List<Sell> storeSells(Integer idStore) {
		
		return sellDao.storeSells(idStore);
	}

	@Override
	public List<Sell> annualSells(LocalDate dateUntil) {
		
		return sellDao.annualSells(java.sql.Date.valueOf(dateUntil));
	}

	@Override
	public List<Object> topSell() {
		
		return sellDao.topSell();
	}
	
	

}
