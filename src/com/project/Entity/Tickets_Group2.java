package com.project.Entity;

import java.util.Vector;

/*
 * Tickets Class
 */
public class Tickets_Group2 {
	
	private int ticket_number;
	private String email = "";
	private String issue = "";
	private String status = "";
	private String date = "";
	private String added_by="";
	
	
	/*
	 * Getters and Setters 
	 */
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getTicket_number() {
		return ticket_number;
	}
	public void setTicket_number(int ticket_number) {
		this.ticket_number = ticket_number;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIssue() {
		return issue;
	}
	public void setIssue(String issue) {
		this.issue = issue;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getAdded_by() {
		return added_by;
	}
	public void setAdded_by(String added_by) {
		this.added_by = added_by;
	}
	
	public Vector getVector()	{
		Vector v = new Vector();
		v.add(ticket_number);
		v.add(email);
		v.add(issue);
		v.add(status);
		v.add(date);
		v.add(added_by);
		return v;
	}

}
