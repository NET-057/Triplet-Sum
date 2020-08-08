package com.creditsaison.tripletsum.dao;

import java.util.List;

import io.ebean.Ebean;
import io.ebean.EbeanServer;
import io.ebean.Query;

public class CommonDaoImpl<T> implements CommonDao<T>{

	Class<T> classOfTypeT;

	public CommonDaoImpl(Class<T> classOfTypeT) {
		this.classOfTypeT = classOfTypeT;
	}
	
	public EbeanServer getDB() {
		return Ebean.getDefaultServer();
	}

	@Override
	public void save(T bean) {
		getDB().save(bean);
		
	}

	@Override
	public void save(List<T> beans) {
		getDB().save(beans);
		
	}
	
	public Query<T> findFromDb() {
		Query<T> find = getDB().find(this.classOfTypeT);
		return find;
	}

}
