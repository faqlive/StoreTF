package com.fjl.storemanagment.interfaces;

import java.time.LocalDate;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import com.fjl.storemanagment.generic.IGenericService;
import com.fjl.storemanagment.model.Sell;


public interface ISellService extends IGenericService<Sell, Integer, Integer> {
	
	public Page<Sell> getAllBetween(LocalDate dateSicen, LocalDate dateUntil, Pageable paging);
	
	public Page<Sell> getInStoreAllBetween(LocalDate dateSicen, LocalDate dateUntil,
											Integer idStore, Pageable paging);

	public Page<Sell> getAllForangeKey(Integer idStore, Pageable paging);

}
