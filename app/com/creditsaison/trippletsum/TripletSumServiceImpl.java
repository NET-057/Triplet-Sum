package com.creditsaison.trippletsum;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.validation.ValidationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.creditsaison.tripletsum.dao.TrippletSumDao;
import com.creditsaison.tripletsum.vo.SuccessVO;
import com.creditsaison.tripletsum.vo.TripletHistoryVO;
import com.creditsaison.tripletsum.vo.TripletSumVO;
import com.creditsaison.trippletsum.utils.TrippletSumConversionUtils;
import com.creditsaison.trippletsum.utils.TrippletSumUtils;
import com.creditsaison.trippletsum.utils.ValidationUtils;
import com.google.inject.Inject;

import models.TripletSum;

public class TripletSumServiceImpl implements TripletSumService{

	private static final Logger LOGGER = LoggerFactory.getLogger(TripletSumServiceImpl.class);
	private TrippletSumDao trippletSumDao;

	@Inject
	public TripletSumServiceImpl(TrippletSumDao trippletSumDao) {
		this.trippletSumDao = trippletSumDao;
	}



	@Override
	public TripletSumVO getTripletSum(TripletSumVO requestVO) {
		TripletSumVO trippletSumResponseVO = new TripletSumVO();
		try {
			isValidInput(requestVO);
			List<Integer> inputArray = requestVO.getInputArray();
			Integer requiredSum = requestVO.getRequiredSum();
			List<Integer> tripplet = getTripplet(inputArray, requiredSum);
			saveResultDB(inputArray, requiredSum, tripplet);
			updateResponse(requestVO, tripplet, trippletSumResponseVO);

		} catch (ValidationException validEx) {
			LOGGER.warn("validation Error for "+requestVO,validEx.getMessage(),validEx);
			SuccessVO.updateSuccessVO(trippletSumResponseVO, false, validEx.getMessage());

		} catch (Exception e) {
			LOGGER.error("Error during calculate sum tripplet for "+requestVO,e);
			SuccessVO.updateSuccessVO(trippletSumResponseVO, false, "ISE");
		}
		return trippletSumResponseVO;

	}


	private TripletSumVO updateResponse(TripletSumVO requestVO, List<Integer> tripplet, TripletSumVO trippletSumVO) {
		trippletSumVO.setInputArray(requestVO.getInputArray());
		trippletSumVO.setRequiredSum(requestVO.getRequiredSum());
		trippletSumVO.setOutputArray(tripplet);
		trippletSumVO.setIsSuccesful(true);
		return trippletSumVO;
	}



	private void saveResultDB(List<Integer> inputArray, Integer requiredSum, List<Integer> tripplet) {
		TripletSum trippletSum = new TripletSum();
		String inputArAsString = TrippletSumUtils.getString(inputArray);
		String outputArAsString = TrippletSumUtils.getString(tripplet);
		trippletSum.setInputArray(inputArAsString);
		trippletSum.setOutputArray(outputArAsString);
		trippletSum.setRequiredSum(requiredSum);
		trippletSumDao.savebeans(trippletSum);

	}



	public List<Integer> getTripplet(List<Integer> inputArray, Integer requiredSum){
		int arraySize = inputArray.size();
		Collections.sort(inputArray);
		int start = -1;
		int end = -1;
		int i = -1;
		for (i = 0; i < arraySize - 2; i++) { 
			start = i + 1; 
			end = arraySize - 1; 
			while (start < end) { 
				if (inputArray.get(i) + inputArray.get(start) + inputArray.get(end) == requiredSum) { 
					return getTripletArray(inputArray, i, start, end);

				} else if (inputArray.get(i) + inputArray.get(start) + inputArray.get(end) < requiredSum) {
					start++; 

				}else {
					end--; 	            	
				}
			} 
		}
		return new LinkedList<>();
	}


	private List<Integer> getTripletArray(List<Integer> inputArray, int p1, int p2, int p3) {
		List<Integer> outputArray = new LinkedList<>();
		if(p1 > -1 && p2 > -1 && p3 > -1) {
			outputArray.add(inputArray.get(p1));
			outputArray.add(inputArray.get(p2));
			outputArray.add(inputArray.get(p3));
		}
		return outputArray;

	}

	@Override
	public TripletHistoryVO getTripletSumHistory() {
		List<TripletSum> allEntries = trippletSumDao.getAllEntries();
		return TrippletSumConversionUtils.toVos(allEntries);

	}


	private void isValidInput(TripletSumVO requestVO) {
		ValidationUtils.notNull(requestVO.getRequiredSum(), "Sum should not be null");
		ValidationUtils.notEmpty(requestVO.getInputArray(), "input array size should be greater then 3");

	}


	public static void main(String[] args) {
		System.out.println("HI");
	}

}
