package com.fjl.storemanagment.controller;

import java.time.LocalDate;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fjl.storemanagment.model.PisID;
import com.fjl.storemanagment.model.ProductInStore;
import com.fjl.storemanagment.model.Sell;
import com.fjl.storemanagment.service.PisService;
import com.fjl.storemanagment.service.ProductService;
import com.fjl.storemanagment.service.SellService;
import com.fjl.storemanagment.service.StoreService;
import com.fjl.storemanagment.util.MarketCart;

import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Controller
@RequestMapping("/Market")
public class MarketController {

	@Autowired
	private PisService servicePis;
	@Autowired
	private SellService serviceSell;
	
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
		
		ProductInStore addPis = null;
		ProductInStore origPis = servicePis.get(new PisID(idStore,idProduct));
		
		if(idStore != 0 && idProduct != 0) {
			addPis = new ProductInStore();
			addPis.setId(new PisID(idStore,idProduct));
			addPis.setProduct(serviceProduct.get(idProduct));
			addPis.setStore(serviceStore.get(idStore));
			addPis.setStock(1);
		
		}else {
			
		}
			
		if(session.getAttribute("cart") == null) {
			cart = cart.getMarketCart();	
		}else {
			cart = (MarketCart) session.getAttribute("cart");	
		}	
		
		cart.add(addPis, origPis);
		

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
		if (cart.size()==0)	session.removeAttribute("cart");
		else	session.setAttribute("cart", cart);
		
		return"user/market_cart";
	}
	
	@RequestMapping("/clearCart")
	public String clearCart(HttpServletRequest request) {
		HttpSession session = null;
		if (request.getSession() == null) {
			 session = request.getSession(true);
		}else {
			session = request.getSession();
			session.removeAttribute("cart");
		}
				
		return"user/market_cart";
	}
	
	@RequestMapping("/less")
	public String lessStock(Model model, HttpServletRequest request,
									@RequestParam("idStore") Integer idStore,
									@RequestParam("idProduct") Integer idProduct,
									@RequestParam(name="quantity", defaultValue = "1") Integer quantity) {
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
		ProductInStore lessPis = new ProductInStore(new PisID(idStore,idProduct),quantity);
		cart.less(lessPis);
		
		if (cart.size()==0)	session.removeAttribute("cart");
		else	session.setAttribute("cart", cart);
		
		return "user/market_cart";
	}
	
	@RequestMapping("/buy")
	public String buy (Model model, HttpServletRequest request) {
		String msj = null;
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
		
		try {
		cart.getCart().stream().forEach(pis -> {
			serviceSell.sell(pis.getId().getIdStore(), pis.getId().getIdProduct(), LocalDate.now());;
			servicePis.sell(pis);
			
		});
		
		session.removeAttribute("cart");
			msj = "COMPRA EXITOSA";
		}catch(Exception e){
			msj = "Algo fallo vuelva a intentarlo";
		}finally {
			model.addAttribute("msj", msj);
		}
		
		
		return "user/market_cart";
	}
	
}
