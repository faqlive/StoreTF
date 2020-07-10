package com.fjl.storemanagment.generic;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



public abstract class GenericService<T, ID extends Serializable, FK> implements IGenericService<T, ID, FK>{

	@Override
	public void save(T enteity) {
		getDao().save(enteity);
		
	}

	@Override
	public void delete(ID id) {
		getDao().deleteById(id);;
	}

	@Override
	public T get(ID id) {
		 Optional<T> op = getDao().findById(id);
		 return op.get();
	}

	@Override
	public Page<T> getAll(Pageable paging) {	
		return getDao().findAll(paging);
	}
	
	@Override
	public List<T> getAll(){
		return getDao().findAll();
	}
	
    
	public abstract List<T> getAllForangeKey(FK forangeKey);

	public abstract Page<T> getAllForangeKey(FK forangeKey, Pageable paging);

	public abstract IGenericDao<T, ID, FK> getDao();
	
}
