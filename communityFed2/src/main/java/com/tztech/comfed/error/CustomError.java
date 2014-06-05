package com.tztech.comfed.error;

public class CustomError {
	public Integer errorId ;
	public String errorMessage;
	
	public CustomError(Integer id){
		errorId = id;
	}

	
	public Integer getErrorId() {
		return errorId;
	}
	public void setErrorId(Integer errorId) {
		this.errorId = errorId;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
