package com.cisco.chat.actionform;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

public class welcomeActionForm extends ActionForm{

	private String username;
	private String password;
	private ArrayList oluserlist; 

	public ArrayList getOluserlist() {
		return oluserlist;
	}
	public void setOluserlist(ArrayList oluserlist) {
		this.oluserlist = oluserlist;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
