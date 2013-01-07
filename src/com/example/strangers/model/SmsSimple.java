package com.example.strangers.model;

public class SmsSimple {

	private String address;
	private String body;
	
	public SmsSimple(String address, String body) {
		this.address = address;
		this.body = body;
	}
	
	
	public void setAddress(String address) {
		this.address = address;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
	
	public String getAddress() {
		return address;
	}
	public String getBody() {
		return body;
	}
	
	
}
