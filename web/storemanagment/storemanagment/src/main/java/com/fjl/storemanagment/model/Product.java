package com.fjl.storemanagment.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="products", schema ="store")
@Data @AllArgsConstructor @NoArgsConstructor
public class Product implements Comparable<Product>{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idProduct;
    private String nameProduct;
    private double priceProduct;
    
    
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

    
}
