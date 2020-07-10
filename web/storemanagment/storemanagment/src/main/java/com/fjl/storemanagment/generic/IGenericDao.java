package com.fjl.storemanagment.generic;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IGenericDao<T, ID extends Serializable, FK> extends JpaRepository<T, ID>{
	
	/**
	 * Todos los que implemente esta interface tb implementaran PagingAndSortingRepository
	 * */

}
