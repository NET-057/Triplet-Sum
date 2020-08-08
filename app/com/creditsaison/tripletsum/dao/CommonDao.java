package com.creditsaison.tripletsum.dao;

import java.util.List;

public interface CommonDao<T> {

	void save(T bean);

	void save(List<T> beans);
}
