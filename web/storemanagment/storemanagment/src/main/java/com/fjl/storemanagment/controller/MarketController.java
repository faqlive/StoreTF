package com.fjl.storemanagment.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;
import com.fjl.storemanagment.model.MarketCart;
import com.fjl.storemanagment.model.PisID;
import com.fjl.storemanagment.model.ProductInStore;
import com.fjl.storemanagment.service.PisService;
import com.fjl.storemanagment.service.ProductService;
import com.fjl.storemanagment.service.StoreService;

import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Controller
@RequestMapping("/Market")
public class MarketController {

	@Autowired
	private PisService servicePis;
	@Autowired
	private ProductService serviceProduct;
	@Autowired
	private StoreService serviceStore;

	@Autowired
	private MarketCart cart;

	/**
	 * Añadir a al carrito de compra el producto deseado.
	 * 
	 * 
	 * */
	@RequestMapping("/addCart")
	public String buy(Model model, HttpServletRequest request,
					@RequestParam("idStore") Integer idStore,
					@RequestParam("idProduct") Integer idProduct,
					@RequestParam("stock") Integer stock,
					@RequestParam("pageNo") Optional<Integer> pageNo,
					@RequestParam(value = "pageSize",required = false) Optional<Integer> pageSize) {
		HttpSession session = null;
		//Pagina previa
		String prevPag = request.getHeader("Referer").substring("http://localhost:8080/".length()-1);
		
		if (request.getSession() == null) {
			 session = request.getSession(true);
		}else {
			session = request.getSession();
		}
		
		ProductInStore pis = null;
		
		if(idStore != 0 && idProduct != 0) {
			pis = new ProductInStore();
			pis.setId(new PisID(idStore,idProduct));
			pis.setProduct(serviceProduct.get(idProduct));
			pis.setStore(serviceStore.get(idStore));
			// solo coge uno.
			pis.setStock(1);
			///////////////////////
			//servicePis.sell(pis);
			
			///////////////////////
		}else {
		// mostrar mensaje de error	
		}
			
		if(session.getAttribute("cart") == null) {
			cart = cart.getMarketCart();	
		}else {
			cart = (MarketCart) session.getAttribute("cart");
		}	

		cart.add(pis);

		model.addAttribute("stock", stock);
		session.setAttribute("cart", cart);
		model.addAttribute("idStore",idStore);
		
		// mostrar mensaje carrito
		return "redirect:".concat(prevPag);
	}
	
	
	@RequestMapping("/list")
	public String list(HttpServletRequest request) {
		HttpSession session = null;
		if (request.getSession() == null) {
			 session = request.getSession(true);
		}else {
			session = request.getSession();
		}
		
		if(session.getAttribute("cart") == null) {
			cart = cart.getMarketCart();
			
		}else {
			cart = (MarketCart) session.getAttribute("cart");
		}
		session.setAttribute("cart", cart);
		
		return"user/market_cart";
	}
	
}
