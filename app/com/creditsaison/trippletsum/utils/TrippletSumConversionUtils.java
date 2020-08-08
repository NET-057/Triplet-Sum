package com.creditsaison.trippletsum.utils;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.creditsaison.tripletsum.vo.TripletHistoryVO;
import com.creditsaison.tripletsum.vo.TripletSumVO;

import models.TripletSum;

public class TrippletSumConversionUtils {

	public static TripletSumVO toVo(TripletSum trippletSum) {

		TripletSumVO trippletVO  = new TripletSumVO();
		List<Long> inputArray = getInputArray(trippletSum);
		trippletVO.setInputArray(inputArray);
		trippletVO.setRequiredSum(trippletSum.getRequiredSum());
		List<Long> outputArray = getOutputArray(trippletSum);
		trippletVO.setOutputArray(outputArray);
		return trippletVO;
	}

	private static List<Long> getOutputArray(TripletSum trippletSum) {
		if(trippletSum.getOutputArray() == null) {
			return new LinkedList<>();
		}
		return getArrayList(trippletSum.getOutputArray());
	}

	private static List<Long> getInputArray(TripletSum trippletSum){
		return getArrayList(trippletSum.getInputArray());
	}
	
	private static List<Long> getArrayList(String arrayAsString){
		if(arrayAsString != null) {
			String[] split = arrayAsString.split(",");
			return Stream.of(split).map(d1 -> Long.parseLong(d1)).collect(Collectors.toList());
		}
		return new LinkedList<>();
	}
	
	public static TripletHistoryVO toVos(List<TripletSum> trippletSums) {
		TripletHistoryVO trippletHistory = new TripletHistoryVO();
		if(trippletSums != null && trippletSums.size() > 0) {
			List<TripletSumVO> trippletSumsVO = trippletSums.stream().map(s1 -> toVo(s1)).collect(Collectors.toList());
			trippletHistory.setTrippletRequestVOs(trippletSumsVO);
		}
		return trippletHistory;
	}
}
