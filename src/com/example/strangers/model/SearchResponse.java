package com.example.strangers.model;

public class SearchResponse {

	/* ******************** *
	 * ****** Fields ****** *
	 * ******************** */
	
	private String responseText;
	private String reponseAccount;
	private String mailDate;

	
	/* ************************* *
	 * ****** Constructor ****** *
	 * ************************* */
	
	public SearchResponse(String text) {
		super();
		this.responseText = text;
	}

	
	/* ********************* *
	 * ****** Getters ****** *
	 * ********************* */
	
	public String getResponseText() {
		return responseText;
	}
	
	public String getReponseAccount() {
		return reponseAccount;
	}
	
	public String getMailDate() {
		return mailDate;
	}
	
	
	/* ********************* *
	 * ****** Setters ****** *
	 * ********************* */
	
	public void setResponseText(String responseText) {
		this.responseText = responseText;
	}

	public void setReponseAccount(String reponseAccount) {
		this.reponseAccount = reponseAccount;
	}
	
	public void setMailDate(String mailDate) {
		this.mailDate = mailDate;
	}

}
