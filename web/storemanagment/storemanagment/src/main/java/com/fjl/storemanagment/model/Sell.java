package com.fjl.storemanagment.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="sales")
@Data @AllArgsConstructor @NoArgsConstructor
public class Sell {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idSell;
    private int idProduct;
    private int idStore;
    @Column(name="year")
    private LocalDate date;
    
}
