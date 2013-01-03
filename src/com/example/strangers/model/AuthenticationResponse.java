package com.example.strangers.model;

public class AuthenticationResponse {
	
	private String message;
	private String token;
	private long expiration;
	
	
	public AuthenticationResponse(String message, String token, long expiration) {
		this.message = message;
		this.token = token;
		this.expiration = expiration;
	}
	
	public AuthenticationResponse() {}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public long getExpiration() {
		return expiration;
	}
	public void setExpiration(long expiration) {
		this.expiration = expiration;
	}
	
	

}
