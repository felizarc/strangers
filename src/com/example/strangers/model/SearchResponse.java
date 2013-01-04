package com.example.strangers.model;

public class SearchResponse {

	/* ******************** *
	 * ****** Fields ****** *
	 * ******************** */
	
	private String responseMailAccount;
	private String responseMailExpeditor;
	private String responseMailDate;
	private String responseMailContent;
	private String responseMailShortContent;

	
	/* ************************* *
	 * ****** Constructor ****** *
	 * ************************* */
	
	public SearchResponse(String responseMailAccount, String responseMailExpeditor, String responseMailDate, String responseMailContent, String responseMailShortContent) {
		this.responseMailAccount = responseMailAccount;
		this.responseMailExpeditor = responseMailExpeditor;
		this.responseMailDate = responseMailDate;
		this.responseMailContent = responseMailContent;
		this.responseMailShortContent = responseMailShortContent;
	}
	
	
	/* ********************* *
	 * ****** Getters ****** *
	 * ********************* */
	
	public String getResponseMailAccount() {
		return responseMailAccount;
	}

	public String getResponseMailExpeditor() {
		return responseMailExpeditor;
	}

	public String getResponseMailDate() {
		return responseMailDate;
	}

	public String getResponseMailContent() {
		return responseMailContent;
	}

	public String getResponseMailShortContent() {
		return responseMailShortContent;
	}
	
	
	/* ********************* *
	 * ****** Setters ****** *
	 * ********************* */
	
	public void setResponseMailAccount(String responseMailAccount) {
		this.responseMailAccount = responseMailAccount;
	}

	public void setResponseMailExpeditor(String responseMailExpeditor) {
		this.responseMailExpeditor = responseMailExpeditor;
	}

	public void setResponseMailDate(String responseMailDate) {
		this.responseMailDate = responseMailDate;
	}

	public void setResponseMailContent(String responseMailContent) {
		this.responseMailContent = responseMailContent;
	}

	public void setResponseMailShortContent(String responseMailShortContent) {
		this.responseMailShortContent = responseMailShortContent;
	}

}
