package com.fjl.storemanagment.interfaces;


import com.fjl.storemanagment.generic.IGenericService;
import com.fjl.storemanagment.model.PisID;
import com.fjl.storemanagment.model.ProductInStore;

public interface IPisService extends IGenericService<ProductInStore, PisID, Integer>{
	
	String saveOrUpdate(ProductInStore entity);

}
