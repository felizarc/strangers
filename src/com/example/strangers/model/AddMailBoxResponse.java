package com.example.strangers.model;

public class AddMailBoxResponse {
	
	
	/* ******************** *
	 * ****** Fields ****** *
	 * ******************** */
	
	int status;
	//TODO check the response variables

	
	/* ************************* *
	 * ****** Constructor ****** *
	 * ************************* */
	
	public AddMailBoxResponse(int status) {
		super();
		this.status = status;
	}
	
	
	/* ********************* *
	 * ****** Getters ****** *
	 * ********************* */
	
	public int getStatus() {
		return status;
	}

	
	/* ********************* *
	 * ****** Setters ****** *
	 * ********************* */
	
	public void setStatus(int status) {
		this.status = status;
	}

	
	
	

}
