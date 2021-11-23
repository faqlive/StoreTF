package com.fjl.storemanagment.dto;

import com.fjl.storemanagment.model.StoreHome;

import lombok.Data;

@Data
public class StoreReport {
	
	private StoreHome storeHome;
	private Integer riskProduct;
	private Integer breakProduct;
	
	public StoreReport(StoreHome storeHome,Integer riskProduct,Integer breakProduct){
		this.storeHome = storeHome;
		this.breakProduct = breakProduct;
		this.riskProduct = riskProduct;
	}
	

}
