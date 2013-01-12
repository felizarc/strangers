package com.example.strangers.model;

public class AccountResponse {

	/* ******************** *
	 * ****** Fields ****** *
	 * ******************** */
	
	private int id;
	private int user_id;
	private String host;
	private int port;
	private String username;
	private String description;
	
	
	/* ************************* *
	 * ****** Constructor ****** *
	 * ************************* */
	
	public AccountResponse(int id, int user_id, String host, int port, String username, String description) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.host = host;
		this.port = port;
		this.username = username;
		this.description = description;
	}


	/* ********************* *
	 * ****** Getters ****** *
	 * ********************* */
	
	public int getId() {
		return id;
	}
	
	public int getUser_id() {
		return user_id;
	}
	
	public String getHost() {
		return host;
	}
	
	public int getPort() {
		return port;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getDescription() {
		return description;
	}
	
	
	/* ********************* *
	 * ****** Setters ****** *
	 * ********************* */

	public void setId(int id) {
		this.id = id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
