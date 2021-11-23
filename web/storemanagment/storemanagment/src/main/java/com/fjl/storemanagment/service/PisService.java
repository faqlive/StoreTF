package com.fjl.storemanagment.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fjl.storemanagment.dao.IPisDao;
import com.fjl.storemanagment.generic.GenericService;
import com.fjl.storemanagment.generic.IGenericDao;
import com.fjl.storemanagment.interfaces.IPisService;
import com.fjl.storemanagment.model.PisID;
import com.fjl.storemanagment.model.ProductInStore;
@Service
public class PisService extends GenericService<ProductInStore,PisID,Integer> implements IPisService {
	
	
	@Autowired
	private IPisDao pisDao;
	
	
	@Override
	public IGenericDao<ProductInStore,PisID,Integer> getDao() {
		return pisDao;
	}

	@Override
	public Page<ProductInStore> getAllForangeKey(Integer forangeKey,Pageable paging) {
		// PENSAR EN COMO DIFERENCIAR ID PRODUCTO DE ID STORE.
		// pisDao.getAllByIdProduct(forangeKey);
		return pisDao.getAllByIdStore(forangeKey, paging);
	}

	@Deprecated
	@Override
	public List<ProductInStore> getAllForangeKey(Integer forangeKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String saveOrUpdate(ProductInStore entity) {
		Optional<ProductInStore> pisPreExisting = pisDao.findById(entity.getId());
		String accion = "";
		if(pisPreExisting.isPresent()) {
			Integer newStock = pisPreExisting.get().getStock() + entity.getStock();
			Integer idProcut = entity.getProduct().getIdProduct();
			Integer idStore = entity.getStore().getIdStore();
			pisDao.update(idStore,idProcut, newStock);	
			accion = "Se ha aumentado "+ entity.getStock() +" unidades";
		}else {
			pisDao.save(entity);
			accion = "Se ha dado de alta";
		}	
		return accion;
	}
	/**
	 * 
	 * */
	@Override
	public void sell(ProductInStore entity) {
		Optional<ProductInStore> pisPreExisting = pisDao.findById(entity.getId()); 
		if(pisPreExisting.isPresent()) {
			Integer newStock = pisPreExisting.get().getStock() - entity.getStock();
			Integer idProcut = entity.getProduct().getIdProduct();
			Integer idStore = entity.getStore().getIdStore();

			if(newStock == 0) {
				pisDao.delete(entity);
			}else {
				pisDao.update(idStore,idProcut, newStock);	
			}
		}
	}

	@Override
	public Page<ProductInStore> ceckRiskStockByStore(Integer idStore , Optional<Integer> riskStock,Pageable paging) {
		Integer riskStockBase = riskStock.orElse(5);
		return pisDao.ceckRiskStockByStore(idStore,riskStockBase,paging);
		
	}

	@Override
	public Integer countBreakStockByStore(Integer idStore) {
		
		return pisDao.countBreakStockByStore(idStore);
	}

	@Override
	public Integer countRiskStockByStore(Integer idStore, Optional<Integer> riskStock) {
		
		return pisDao.countRiskStockByStore(idStore, riskStock);
	}


}
