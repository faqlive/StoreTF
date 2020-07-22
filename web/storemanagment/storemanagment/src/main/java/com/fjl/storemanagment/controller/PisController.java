package com.fjl.storemanagment.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String list (Model model, @PathVariable("idStore") Integer idStore,
			@RequestParam("pageNo") Optional<Integer> pageNo,
			@RequestParam(value = "pageSize",required = false) Optional<Integer> pageSize ) {
		
		Map<Product, Integer> mapPis = new HashMap<>();
		
		int page = pageNo.orElse(0);
		int size = pageSize.orElse(10);		
		Pageable paging = PageRequest.of(page, size);
		
		Page<ProductInStore> listPis = servicePis.getAllForangeKey(idStore,paging);
		listPis.forEach(pis -> mapPis.put(serviceProduct.get(pis.getId().getIdProduct()), pis.getStock()));
		
		
		// CONSULTA CADA PRODUCTO EN STOCK Y LO GUARDA EN LA LISTA
		
		
		// SE PUEDE REFACTORIZAR
		int totalPage = listPis.getTotalPages();
		if (totalPage > 0) {
			List<Integer> listPage = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
			model.addAttribute("listPage",listPage);
		}
		int firstValue=0;
		int lastValue=0;
		if(page < 4) {
			firstValue = 0;
			lastValue = 5;
		}else if (page > (totalPage - 5)){
			firstValue = totalPage - 5;
			lastValue = totalPage;
		}else {
			firstValue = page - 2;
			lastValue = page + 3;
		}
		model.addAttribute("firstValue", firstValue);
		model.addAttribute("lastValue", lastValue);
		model.addAttribute("curretPage", page);
		model.addAttribute("lastPage", totalPage);
		//FIN DE REFACTORIZAR
		
		model.addAttribute("idStore",idStore);
		TreeMap<Product, Integer> sorted = new TreeMap<>(mapPis);
		
		model.addAttribute("mapPis", sorted);
		model.addAttribute("store",serviceStore.get(idStore));
		
		return "user/pis";
	}

}
