package com.example.strangers.model;

import java.util.ArrayList;

public class User {
	
	/* ******************** *
	 * ****** Fields ****** *
	 * ******************** */
	private int id;
	private String login;
	private String password;
	private ArrayList<Account> accountList;
	
	
	/* ************************* *
	 * ****** Constructor ****** *
	 * ************************* */
	
	public User(String login, ArrayList<Account> accountList) {
		this.login = login;
		this.accountList = accountList;
	}
	
	/* ********************* *
	 * ****** Getters ****** *
	 * ********************* */
	
	public int getId() {
		return id;
	}
	
	public String getLogin() {
		return login;
	}
	
	public String getPassword() {
		return password;
	}
	
	public ArrayList<Account> getAccountList() {
		return accountList;
	}

	
	/* ********************* *
	 * ****** Setters ****** *
	 * ********************* */
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setAccountList(ArrayList<Account> accountList) {
		this.accountList = accountList;
	}	
	
}
