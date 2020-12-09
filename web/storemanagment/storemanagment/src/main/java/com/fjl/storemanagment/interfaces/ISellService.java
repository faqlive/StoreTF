package com.fjl.storemanagment.interfaces;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import com.fjl.storemanagment.dto.TotalSales;
import com.fjl.storemanagment.generic.IGenericService;
import com.fjl.storemanagment.model.Sell;


public interface ISellService extends IGenericService<Sell, Integer, Integer> {
	
	public Page<Sell> getAllBetween(LocalDate dateSicen, LocalDate dateUntil, Pageable paging);
	
	public Page<Sell> getInStoreAllBetween(LocalDate dateSicen, LocalDate dateUntil,
											Integer idStore, Pageable paging);

	public Page<Sell> getAllForangeKey(Integer idStore, Pageable paging);
	
	public List<TotalSales> totalSells();
	
	public List<TotalSales> storeSells(Integer idStore);
	
	public List<TotalSales> annualSells(Integer year);
	
	public List<TotalSales> topSell();

}
