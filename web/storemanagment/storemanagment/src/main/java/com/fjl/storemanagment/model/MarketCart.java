package com.fjl.storemanagment.model;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;


@Component
public class MarketCart {
	
	private static List<ProductInStore> cart;

	private MarketCart() {
		MarketCart.cart = new ArrayList<>();	
	}
	
	public MarketCart getMarketCart() {
		return new MarketCart();
	}
	
	protected static List<ProductInStore> getCart() {
		return cart;
	}

	protected static void setCart(List<ProductInStore> cart) {
		MarketCart.cart = cart;	
	}
	
	public void add(ProductInStore pis) {		
		if(cart.contains(pis)) {
			cart.remove(pis);
		}
		cart.add(pis);
	}

	public ProductInStore getPis(Integer idProduct) {
		return cart.get(idProduct);
	}
}
