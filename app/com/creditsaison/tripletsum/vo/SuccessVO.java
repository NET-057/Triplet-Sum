package com.creditsaison.tripletsum.vo;

public class SuccessVO {

	private Boolean isSuccesful;
	private String message;
	private Integer statusCode;

	public static void updateSuccessVO(SuccessVO successVO, Boolean isSuccessful, String message) {
		successVO.setIsSuccesful(isSuccessful);
		successVO.setMessage(message);
	}
	
	public Boolean getIsSuccesful() {
		return isSuccesful;
	}
	public void setIsSuccesful(Boolean isSuccesful) {
		this.isSuccesful = isSuccesful;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Integer getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}
	
	
}
