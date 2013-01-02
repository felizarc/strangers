package com.example.strangers.model;

import java.util.ArrayList;

public class User {
	
	/* ******************** *
	 * ****** Fields ****** *
	 * ******************** */
	
	private String login;
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
	
	public String getLogin() {
		return login;
	}
	
	public ArrayList<Account> getAccountList() {
		return accountList;
	}
	
	/* ********************* *
	 * ****** Setters ****** *
	 * ********************* */
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public void setAccountList(ArrayList<Account> accountList) {
		this.accountList = accountList;
	}
	
}
