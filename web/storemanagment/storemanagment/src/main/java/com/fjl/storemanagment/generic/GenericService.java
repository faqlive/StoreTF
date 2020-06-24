package com.fjl.storemanagment.generic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



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
	public List<T> getAll() {
		List<T> returnList = new ArrayList<>();
		getDao().findAll().forEach(obj -> returnList.add(obj));
		return returnList;
	}

	public abstract List<T> getAllForangeKey(FK forangeKey);

	public abstract IGenericDao<T, ID, FK> getDao();
	
}
