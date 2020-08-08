package com.creditsaison.tripletsum.dao;

import java.util.List;

import models.TripletSum;

public class TrippletSumDaoImpl extends CommonDaoImpl<TripletSum> implements TrippletSumDao{

	public TrippletSumDaoImpl() {
		super(TripletSum.class);
	}
	
	public TrippletSumDaoImpl(Class<TripletSum> classOfTypeT) {
		super(classOfTypeT);
	}

	@Override
	public List<TripletSum> getAllEntries() {
		return findFromDb().findList();
		
	}
	

}
