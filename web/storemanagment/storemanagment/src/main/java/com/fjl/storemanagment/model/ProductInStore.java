package com.fjl.storemanagment.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name="products_stores")
@Data
public class ProductInStore implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PisID id;
	
	@ManyToOne
    @MapsId("idProduct")
    @JoinColumn(name = "idProduct", insertable = false, updatable = false)
	private Product product;
	
    @ManyToOne
    @MapsId("idStore")
    @JoinColumn(name = "idStore", insertable = false, updatable = false)
    private StoreHome store;
	
    private int stock;
    
    public ProductInStore (Integer idStore,Integer idProduct, Integer stock){
    	this();
    	this.id = new PisID(idStore, idProduct);
    	this.product.setIdProduct(idProduct);
    	this.store.setIdStore(idStore);
    	this.stock = stock;    	
    }
    public ProductInStore (Product product, StoreHome store, Integer stock){
    	this();
    	this.store = store;
    	this.product = product;
    }
    public ProductInStore (PisID id, Integer stock){
    	this();
    	this.id = id;
    	this.product.setIdProduct(id.getIdProduct());
    	this.store.setIdStore(id.getIdStore());
       	this.stock = stock;    	
    }
    
    public ProductInStore() {
    	this.store = new StoreHome();
    	this.product = new Product();
    }

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductInStore other = (ProductInStore) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (store == null) {
			if (other.store != null)
				return false;
		} else if (!store.equals(other.store))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + ((store == null) ? 0 : store.hashCode());
		return result;
	}
    
    
}
