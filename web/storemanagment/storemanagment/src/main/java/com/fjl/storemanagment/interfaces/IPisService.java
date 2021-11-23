package com.fjl.storemanagment.interfaces;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fjl.storemanagment.generic.IGenericService;
import com.fjl.storemanagment.model.PisID;
import com.fjl.storemanagment.model.ProductInStore;

public interface IPisService extends IGenericService<ProductInStore, PisID, Integer>{
	
	String saveOrUpdate(ProductInStore entity);
	void sell(ProductInStore entity);
	Page<ProductInStore> ceckRiskStockByStore(Integer idStore ,Optional<Integer> riskStock,Pageable paging);
	Integer countBreakStockByStore(Integer idStore);
	Integer countRiskStockByStore(Integer idStore,Optional<Integer> riskStock);
	

}
