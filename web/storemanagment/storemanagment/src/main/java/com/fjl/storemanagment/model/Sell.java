package com.fjl.storemanagment.model;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name="sales", schema ="store")
@Data @AllArgsConstructor
public class Sell implements Serializable {


	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idSales")
	private int idSell;
/*	
	@ManyToOne
    @JoinColumn(name = "idProduct")
    private int idProduct;
    @ManyToOne
    @JoinColumn(name = "idStore")
    private int idStore;
*/    
    @Column(name="year",nullable=false)
    private LocalDate date;
    
    @ManyToOne
    @JoinColumn(name = "idStore", insertable = false, updatable = false,nullable=false)
    private StoreHome sellSotre;
    
    @ManyToOne
    @JoinColumn(name = "idProduct", insertable = false, updatable = false,nullable=false)
    private Product sellProduct;
    
    public Sell() {
    	this.sellProduct = new Product();
    	this.sellSotre = new StoreHome();
    }

    void setSellProduct(int idProduct){
    	this.sellProduct.setIdProduct(idProduct); 
    }
    
    void setsellSotre(int idStore) {
    	this.sellSotre.setIdStore(idStore);
    }

	public Sell(Product product, StoreHome store) {
		this.sellProduct = product;
    	this.sellSotre = store;
	}
    
}
