package com.project.Entity;

import java.util.Vector;

public class Tickets_Group2 {
	
	private int ticket_number;
	private String email = "";
	private String issue = "";
	
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
	
	public Vector getVector()	{
		Vector v = new Vector();
		v.add(ticket_number);
		v.add(email);
		v.add(issue);
		
		return v;
	}

}
