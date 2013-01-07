package com.example.strangers.model;

public class SearchResponse {

	/* ******************** *
	 * ****** Fields ****** *
	 * ******************** */
	
	private String before;
	private String number;
	private String after;
	private String from;
	private String date;
	private String account;
	private String status;

	
	/* ************************* *
	 * ****** Constructor ****** *
	 * ************************* */
		
	public SearchResponse(String before, String number, String after,
							String from, String date, String account, String status) {

		this.before = before;
		this.number = number;
		this.after = after;
		this.from = from;
		this.date = date;
		this.account = account;
		this.status = status;
	}

	
	/* ********************* *
	 * ****** Getters ****** *
	 * ********************* */
	
	public String getBefore() {
		return before;
	}

	public String getNumber() {
		return number;
	}

	public String getAfter() {
		return after;
	}

	public String getFrom() {
		return from;
	}

	public String getDate() {
		return date;
	}

	public String getAccount() {
		return account;
	}

	public String getStatus() {
		return status;
	}

	
	/* ********************* *
	 * ****** Setters ****** *
	 * ********************* */
	
	public void setBefore(String before) {
		this.before = before;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public void setAfter(String after) {
		this.after = after;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public void setStatus(String status) {
		this.status = status;
	}	
	
}
