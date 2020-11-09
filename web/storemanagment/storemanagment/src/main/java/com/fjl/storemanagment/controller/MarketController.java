package com.fjl.storemanagment.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.fjl.storemanagment.model.MarketCart;
import com.fjl.storemanagment.model.PisID;
import com.fjl.storemanagment.model.ProductInStore;
import com.fjl.storemanagment.service.PisService;
import com.fjl.storemanagment.service.ProductService;
import com.fjl.storemanagment.service.StoreService;

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


	@RequestMapping("/addCart")
	public String buy(Model model, HttpSession session,
					@RequestParam("idStore") Integer idStore,
					@RequestParam("idProduct") Integer idProduct,
					@RequestParam("stock") Integer stock) {

		ProductInStore pis = null;
		if(idStore != 0 && idProduct != 0) {
			pis = servicePis.get(new PisID(idStore,idProduct));
			pis.setProduct(serviceProduct.get(idProduct));
			pis.setStore(serviceStore.get(idStore));
		//	model.addAttribute("pis",pis);
		}else {
			return "error";
		}
		
		
		if(session.getAttribute("cart") == null) {
			cart = cart.getMarketCart();
		
		}else {
			cart = (MarketCart) session.getAttribute("cart");
		}
		
		cart.add(pis);
		
		
		model.addAttribute("stock", stock);
		cart.getCart().forEach(p -> System.out.println(p));
		session.setAttribute("cart", cart.getCart());
		
		return "user/market_cart";	
	}
	
}
