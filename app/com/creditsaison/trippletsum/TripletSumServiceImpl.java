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
			List<Long> inputArray = requestVO.getInputArray();
			Long requiredSum = requestVO.getRequiredSum();
			List<Long> tripplet = getTripplet(inputArray, requiredSum);
			saveResultDB(inputArray, requiredSum, tripplet);
			updateResponse(requestVO, tripplet, trippletSumResponseVO);
			
		} catch (ValidationException validEx) {
			LOGGER.warn("validation Error "+requestVO,validEx.getMessage());
			SuccessVO.updateSuccessVO(trippletSumResponseVO, false, validEx.getMessage());
		
		} catch (Exception e) {
			LOGGER.error("Error during calculate sum tripplet for "+requestVO,e);
			SuccessVO.updateSuccessVO(trippletSumResponseVO, false, "ISE");
		}
		return trippletSumResponseVO;
		
	}
	

	private TripletSumVO updateResponse(TripletSumVO requestVO, List<Long> tripplet, TripletSumVO trippletSumVO) {
		trippletSumVO.setInputArray(requestVO.getInputArray());
		trippletSumVO.setRequiredSum(requestVO.getRequiredSum());
		trippletSumVO.setOutputArray(tripplet);
		trippletSumVO.setIsSuccesful(true);
		return trippletSumVO;
	}



	private void saveResultDB(List<Long> inputArray, Long requiredSum, List<Long> resultantArray) {
		TripletSum trippletSum = new TripletSum();
		String inputArAsString = TrippletSumUtils.getString(inputArray);
		String outputArAsString = TrippletSumUtils.getString(resultantArray);
		trippletSum.setInputArray(inputArAsString);
		trippletSum.setOutputArray(outputArAsString);
		trippletSum.setRequiredSum(requiredSum);
		trippletSumDao.save(trippletSum);
		
	}



	public List<Long> getTripplet(List<Long> arrayInput, Long totalSum){
		int arraySize = arrayInput.size();
		Collections.sort(arrayInput);
		int start = -1;
		int end = -1;
		int i = -1;
		boolean isTrippletFound = false;
	    for (i = 0; i < arraySize - 2; i++) { 
	        start = i + 1; 
	        end = arraySize - 1; 
	        while (start < end) { 
	            if (arrayInput.get(i) + arrayInput.get(start) + arrayInput.get(end) == totalSum) { 
	            	isTrippletFound = true;
	                break; 
	            
	            } else if (arrayInput.get(i) + arrayInput.get(start) + arrayInput.get(end) < totalSum) {
	                start++; 

	            }else {
	            	end--; 	            	
	            }
	        } 
	        
	        if(isTrippletFound) {
	        	break;
	        }
	    }
	    
	    if(isTrippletFound) {
	    	return getTripletArray(arrayInput, start, end, i);
	    	
	    } else {
			return new LinkedList<>();
		}
	}
	
	
	private List<Long> getTripletArray(List<Long> arrayInput, int p1, int p2, int p3) {
		List<Long> outputArray = new LinkedList<Long>();
		if(p1 > 0 && p2 > 0 && p3 > 0) {
			outputArray.add(arrayInput.get(p1));
			outputArray.add(arrayInput.get(p2));
			outputArray.add(arrayInput.get(p3));
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

}
