package com.fjl.storemanagment.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;


@Component
public class MarketCart {
	
	private static List<ProductInStore> cart;

	private MarketCart() {
		MarketCart.cart = new ArrayList<ProductInStore>();	
	}
	
	public MarketCart getMarketCart() {
		return new MarketCart();
	}
	 
	public List<ProductInStore> getCart() {
		return cart;
	}

	protected void setCart(List<ProductInStore> cart) {
		MarketCart.cart = cart;	
	}
	
	public void add(ProductInStore pis) {		
		if(cart.contains(pis)) {
			int indx = cart.indexOf(pis);		
			cart.get(indx).setStock(cart.get(indx).getStock()+ pis.getStock());
		}else {
			cart.add(pis);
		}
	}

	public ProductInStore getPis(Integer idProduct) {
		return cart.get(idProduct);
	}
	/**
	 * Cuenta cuantos Porductos hay en el carrito.
	 * 
	 * */
	public int size() {
		return cart.stream().mapToInt(pis ->pis.getStock()).sum();
	}
	
	public static void subtract(Page<ProductInStore> listPis, List<ProductInStore> listCart) {
		listPis.forEach(pis -> {
			if(listCart.contains(pis)) {
				int ind = listCart.indexOf(pis);
				int cartStock = listCart.get(ind).getStock();
				int rest = pis.getStock() - cartStock;
				if (rest >= 0) pis.setStock(rest);
			}	
		});
		
	}
	
	@Override
	public String toString() {
		return cart.toString();
	}
	 
}
