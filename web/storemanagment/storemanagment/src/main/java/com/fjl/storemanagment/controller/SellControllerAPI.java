package com.fjl.storemanagment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fjl.storemanagment.dto.TotalSales;
import com.fjl.storemanagment.service.SellService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api
@RestController
@RequestMapping("/API")
public class SellControllerAPI {

	@Autowired
	SellService serviceSell;
	
	@ApiOperation(value="Ventas totales")
	@GetMapping("/totalSell")
	public List<TotalSales> totalSell(){
	
		return serviceSell.totalSells();
	}
	
	@ApiOperation(value="Ventas totales por Almacén")
	@GetMapping("/storeSells/{idStore}")
	public List<TotalSales> storeSells(@PathVariable("idStore") Integer idStore){
	
		return serviceSell.storeSells(idStore);
	}
	
	@ApiOperation(value="Ventas totales por periodo anual")
	@GetMapping("/annualSells")
	public List<TotalSales> annualSells(@RequestParam("year") String year){
		
		Integer auxYear =Integer.valueOf(year);
		
		if(auxYear >= 2015 && auxYear <= 2020) {	
			return serviceSell.annualSells(auxYear);
		}else{
			return null;
		}
	}
	
	@ApiOperation(value="TOP 10 Ventas")
	@GetMapping("/topSell")
	public List<TotalSales> topSell(){
	
		return serviceSell.topSell();
	}
	
	
	
}
