package com.fjl.storemanagment.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="products_stores")
@Data @AllArgsConstructor @NoArgsConstructor
public class ProductInStore implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PisID id;
	
    private int stock;
    
    ProductInStore (Integer idStore,Integer idProduct, Integer stock){
    	this.id.setIdProduct(idProduct);
    	this.id.setIdStore(idStore);
    	this.stock = stock;    	
    }
    
    public Integer getIdStore() {
    	return this.id.getIdStore();
    }
    public Integer getIdProduct() {
    	return this.id.getIdProduct();
    }
    
    
}
