/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storemanagment.generic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author FAQ
 */
public abstract class GenericService <T, ID extends Serializable, FK> implements IGenericService<T, ID, FK> {

	@Override
	public void save(T entity) {
		getDao().save(entity);
	}

	@Override
	public void delete(ID id) {
		getDao().delete(id);
	}

	@Override
	public T get(ID id) {
		return getDao().get(id);
	}

	@Override
	public List<T> getAll() {
		List<T> returnList = new ArrayList<>();
		getDao().getAll().forEach(obj -> returnList.add(obj));
		return returnList;
	}
        @Override
	public List<T> getAllForangeKey(FK fkey){
		List<T> returnList = new ArrayList<>();
		getDao().getAllForangeKey(fkey).forEach(obj -> returnList.add(obj));
		return returnList;
	}
	
	public abstract IGenericDao<T, ID, FK> getDao() ;

}
