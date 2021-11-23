package com.fjl.storemanagment.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "idProduct", "nameProduct","stoc","totalAmount" })
public interface TotalSales {
	
	int getIdProduct();
	String getNameProduct();
	int getStock();
    double getTotalAmount();

}
