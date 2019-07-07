package com.project.Entity;

import java.util.Vector;

public class User_Group2 {
	private int user_id = 0;
	private String first_name = "";
	private String last_name = "";
	private String email = "";
	private String address = "";
	private String phone_number = "";
	private String issue="";
	
	
	public String getIssue() {
		return issue;
	}
	public void setIssue(String issue) {
		this.issue = issue;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	
	public Vector toVector() {
		Vector v = new Vector();
		
		v.add(user_id);
		v.add(first_name);
		v.add(last_name);
		v.add(email);
		v.add(address);
		v.add(phone_number);
		return v;
	}
	
}
