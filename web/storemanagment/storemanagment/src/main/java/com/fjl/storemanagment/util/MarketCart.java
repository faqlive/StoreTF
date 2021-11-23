package com.fjl.storemanagment.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import com.fjl.storemanagment.model.ProductInStore;


@Component
public class MarketCart {
	
	private static List<ProductInStore> cart;
	private double totalAmount;
	
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
	/**
	 * Agregar productos al carrito
	 * 
	 * 
	 * */
	public void add(ProductInStore addPis, ProductInStore orgPis) {

		
		if(cart.contains(addPis)) {
			int indx = cart.indexOf(addPis);
			int cartStock = cart.get(indx).getStock();
			int interStock = cartStock + addPis.getStock();
			if((interStock - orgPis.getStock()) <= 0) cart.get(indx).setStock(interStock);			
		}else {
			cart.add(addPis);
		}

	}
	
	public void less(ProductInStore lessPis) {
		if(cart.contains(lessPis)) {
			int indx = cart.indexOf(lessPis);
			int cartStock = cart.get(indx).getStock();
			int interStock = cartStock - lessPis.getStock();
			if((cartStock - lessPis.getStock()) > 0) cart.get(indx).setStock(interStock);
			else cart.remove(lessPis);
		}
	}

	/**
	 * Cuenta cuantos Porductos hay en el carrito.
	 * 
	 * */
	public int size() {
		return cart.stream().mapToInt(pis ->pis.getStock()).sum();
	}
	
	/**
	 * Extrae del la cuenta del stock los productos
	 * añadidos en en carrito del cliente
	 * 
	 * 
	 * */
	public Page<ProductInStore> subtract(Page<ProductInStore> listPis) {
		if (cart != null || !cart.isEmpty()) {
		List<ProductInStore> aux = listPis.getContent(); 
		aux = aux.stream().map(pis -> {
			if(cart.contains(pis)) {
				int indx = cart.indexOf(pis);
				if(pis.getStock()>0) {
				pis.setStock(pis.getStock() - cart.get(indx).getStock());
				}
			}
			return pis;
		}).collect(Collectors.toList());
		
		return new PageImpl<ProductInStore>(aux, listPis.getPageable(), listPis.getTotalElements() );
		}else {
			return listPis;
		}
			
	}
	
	public void setTotalAmount() {
		double price = 0.0;
		if (cart != null && !cart.isEmpty()) {
			price = cart.stream().map(pis -> pis.getStock()*pis.getProduct().getPriceProduct()).reduce(0.0, Double::sum);
		}
		BigDecimal bd = new BigDecimal(price);
		this.totalAmount = bd.setScale(2, RoundingMode.HALF_UP).doubleValue();
	}
	
	public double getTotalAmount() {
		this.setTotalAmount();
		return this.totalAmount;
	}

	@Override
	public String toString() {
		return cart.toString();
	}
	 
}
