package com.example.strangers.model;

import java.util.ArrayList;

public class GeneralSearchNumberResponse {
	
	
	/* ******************** *
	 * ****** Fields ****** *
	 * ******************** */
	
	private ArrayList<SearchResponse> searchResponseList;
	private String status;
	
	
	
	/* ************************* *
	 * ****** Constructor ****** *
	 * ************************* */
	
	public GeneralSearchNumberResponse(ArrayList<SearchResponse> searchResponseList, String status) {
		this.searchResponseList = searchResponseList;
		this.status = status;
	}
	
	
	/* ********************* *
	 * ****** Getters ****** *
	 * ********************* */
	
	public ArrayList<SearchResponse> getSearchResponseList() {
		return searchResponseList;
	}
	
	public String getStatus() {
		return status;
	}
	
	
	/* ********************* *
	 * ****** Setters ****** *
	 * ********************* */
	
	public void setSearchResponseList(ArrayList<SearchResponse> searchResponseList) {
		this.searchResponseList = searchResponseList;
	}


	public void setStatus(String status) {
		this.status = status;
	}
	

}
