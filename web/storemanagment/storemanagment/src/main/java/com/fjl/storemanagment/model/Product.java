package com.fjl.storemanagment.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name="products", schema ="store")
@Data
public class Product implements Serializable, Comparable<Product>{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idProduct;
    private String nameProduct;
    private double priceProduct;
    
    @OneToMany(mappedBy = "product")
    private Set<ProductInStore> stockedProduct = new HashSet<>();
   
    @OneToMany
    @JoinColumn(name="idProduct", nullable=false)
    private Set<Sell> sell;
   
    /**
     * 
     * Ordenación acendente por:
     * @param IdProduct
     * 
     * */
	@Override
	public int compareTo(Product prod) {
		return Integer.compare(this.getIdProduct(), prod.getIdProduct());
	}

    public Product() {
    	
    }
    
    public Product(int idProduct, String nameProduct, double priceProduct) {
    	this.idProduct = idProduct;
    	this.nameProduct = nameProduct;
    	this.priceProduct = priceProduct;
    }

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (idProduct != other.idProduct)
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idProduct;
		return result;
	}

	@Override
	public String toString() {
		return "Product [idProduct=" + idProduct + ", nameProduct=" + nameProduct + ", priceProduct=" + priceProduct
				+ "]";
	}
	    
    
}
