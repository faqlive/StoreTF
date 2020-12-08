package com.fjl.storemanagment.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

import com.fjl.storemanagment.model.MarketCart;
import com.fjl.storemanagment.model.Product;
import com.fjl.storemanagment.model.ProductInStore;
import com.fjl.storemanagment.service.PisService;
import com.fjl.storemanagment.service.ProductService;
import com.fjl.storemanagment.service.StoreService;
import com.fjl.storemanagment.util.Paginador;

import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Controller
@RequestMapping("/PIS")
public class PisController {
	
	@Autowired
	private PisService servicePis;
	@Autowired
	private StoreService serviceStore;
	@Autowired
	private ProductService serviceProduct;
	
	@Autowired
	private MarketCart cart;
	
	@GetMapping("/store/{idStore}")
	public String list (Model model, HttpServletRequest request,
									@PathVariable("idStore") Integer idStore,
									@RequestParam("pageNo") Optional<Integer> pageNo,
									@RequestParam(value = "pageSize",required = false) Optional<Integer> pageSize ) {
		
		Map<Product, Integer> mapPis = new HashMap<>();
		
		int page = pageNo.orElse(0);
		int size = pageSize.orElse(10);		
		Pageable paging = PageRequest.of(page, size);
		
		// Calcular total de paginas
		Page<ProductInStore> listPis = servicePis.getAllForangeKey(idStore,paging);
		listPis.forEach(pis -> mapPis.put(serviceProduct.get(pis.getId().getIdProduct()), pis.getStock()));
		
		int totalPage = listPis.getTotalPages();
		
		//RESTAR CARRITO
		HttpSession session = null;
		if (request.getSession() == null) {
			 session = request.getSession(true);
		}else {
			session = request.getSession();
			if(session.getAttribute("cart") == null) {
				cart = cart.getMarketCart();
			}else {
				cart = (MarketCart) session.getAttribute("cart");
			}
		}
		listPis = cart.subtract(listPis);
		
		// PAGINAR Y ORDENAR	
		Paginador.paginar(model,totalPage,page);
		TreeMap<Product, Integer> sorted = new TreeMap<>(mapPis);
		
		model.addAttribute("idStore",idStore);
		model.addAttribute("mapPis", sorted);
		model.addAttribute("store",serviceStore.get(idStore));
		
		return "user/pis";
	}

}
