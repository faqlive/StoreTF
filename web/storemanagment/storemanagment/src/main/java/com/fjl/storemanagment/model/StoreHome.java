package com.fjl.storemanagment.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="storehouse", schema ="store")
@Data @AllArgsConstructor @NoArgsConstructor
public class StoreHome  implements Serializable {
	
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name="idStore")
	    private int idStore;
	    private String nameStore;
	    private int idLocation;

	    @OneToMany(mappedBy = "store")
	    private Set<ProductInStore> stockedStore = new HashSet<>();;
	    
	    @OneToMany
	    @JoinColumn(name="idStore", nullable=false)
	    private Set<Sell> sell;
	    
	    
	    @Override
	    public String toString() {
	        return "StoreHome{" + "idStore=" + idStore + ", nameStore=" + nameStore + ", idLocation=" + idLocation + '}';
	    }

	    @Override
	    public int hashCode() {
	        int hash = 5;
	        return hash;
	    }

	    @Override
	    public boolean equals(Object obj) {
	        if (obj == null) {
	            return false;
	        }
	        if (getClass() != obj.getClass()) {
	            return false;
	        }
	        final StoreHome other = (StoreHome) obj;
	        if (this.idStore != other.idStore) {
	            return false;
	        }
	        if (!Objects.equals(this.nameStore, other.nameStore)) {
	            return false;
	        }
	        return true;
	    }    
	}

