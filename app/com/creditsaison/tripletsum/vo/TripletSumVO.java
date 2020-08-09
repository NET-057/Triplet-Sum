package com.creditsaison.tripletsum.vo;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TripletSumVO extends SuccessVO{

	@SerializedName("input_array")
	@Expose
	private List<Integer> inputArray;

	@SerializedName("required_sum")
	@Expose
	private Integer requiredSum;

	@SerializedName("triplet_array")
	@Expose
	private List<Integer> outputArray;

	public List<Integer> getInputArray() {
		return inputArray;
	}

	public void setInputArray(List<Integer> inputArray) {
		this.inputArray = inputArray;
	}

	public Integer getRequiredSum() {
		return requiredSum;
	}

	public void setRequiredSum(Integer requiredSum) {
		this.requiredSum = requiredSum;
	}

	public List<Integer> getOutputArray() {
		return outputArray;
	}

	public void setOutputArray(List<Integer> outputArray) {
		this.outputArray = outputArray;
	}



}
