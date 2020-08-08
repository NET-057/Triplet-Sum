package com.creditsaison.tripletsum.dao;

import java.util.List;

import com.google.inject.ImplementedBy;

import models.TripletSum;

@ImplementedBy(TrippletSumDaoImpl.class)
public interface TrippletSumDao extends CommonDao<TripletSum>{

	List<TripletSum> getAllEntries();

	void savebeans(TripletSum tripletSum);

}
