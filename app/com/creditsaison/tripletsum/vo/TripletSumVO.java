package com.creditsaison.tripletsum.vo;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TripletSumVO extends SuccessVO{

	@SerializedName("input_array")
	@Expose
	private List<Long> inputArray;

	@SerializedName("required_sum")
	@Expose
	private Long requiredSum;

	@SerializedName("triplet_array")
	@Expose
	private List<Long> outputArray;

	public Long getRequiredSum() {
		return requiredSum;
	}

	public void setRequiredSum(Long requiredSum) {
		this.requiredSum = requiredSum;
	}

	public List<Long> getInputArray() {
		return inputArray;
	}

	public void setInputArray(List<Long> inputArray) {
		this.inputArray = inputArray;
	}

	public List<Long> getOutputArray() {
		return outputArray;
	}

	public void setOutputArray(List<Long> outputArray) {
		this.outputArray = outputArray;
	}

}
