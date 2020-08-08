package com.creditsaison.trippletsum;

import com.creditsaison.tripletsum.vo.TripletHistoryVO;
import com.creditsaison.tripletsum.vo.TripletSumVO;
import com.google.inject.ImplementedBy;

@ImplementedBy(TripletSumServiceImpl.class)
public interface TripletSumService {

	public TripletSumVO getTripletSum(TripletSumVO fromJson);

	TripletHistoryVO getTripletSumHistory();
}
