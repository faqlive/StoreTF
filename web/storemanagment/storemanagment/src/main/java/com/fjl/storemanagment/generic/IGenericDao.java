package com.fjl.storemanagment.generic;

import java.io.Serializable;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
public interface IGenericDao<T, ID extends Serializable, FK> extends PagingAndSortingRepository<T, ID>{
	
	/**
	 * Todos los que implemente esta interface tb implementaran PagingAndSortingRepository
	 * */

}
