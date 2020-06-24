package com.fjl.storemanagment.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fjl.storemanagment.model.Product;
import com.fjl.storemanagment.model.ProductInStore;
import com.fjl.storemanagment.service.PisService;
import com.fjl.storemanagment.service.ProductService;
import com.fjl.storemanagment.service.StoreService;

@Controller
@RequestMapping("/PIS")
public class PisController {
	
	@Autowired
	private PisService servicePis;
	@Autowired
	private StoreService serviceStore;
	@Autowired
	private ProductService serviceProduct;
	
	@GetMapping("/store/{idStore}")
	public String save (Model model, @PathVariable("idStore") Integer id) {
		Map<Product, Integer> mapPis = new HashMap<>();
		List<ProductInStore> listPis = servicePis.getAllForangeKey(id);
		listPis.forEach(pis -> mapPis.put(serviceProduct.get(pis.getIdProduct()), pis.getStock()));
		TreeMap<Product, Integer> sorted = new TreeMap<>(mapPis);
		model.addAttribute("mapPis", sorted);
		model.addAttribute("store",serviceStore.get(id));
		// CONSULTA CADA PRODUCTO EN STOCK Y LO GUARDA EN LA LISTA
		
		return "user/pis";
	}

}
