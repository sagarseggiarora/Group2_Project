package com.project.Entity;

import java.util.Vector;

public class Logs_Group2 {
	
	private int log_id=0;
	private String ticket_no="";
	private String comment="";
	private String date="";
	private String submited_by="";
	
	public int getLog_id() {
		return log_id;
	}
	public void setLog_id(int log_id) {
		this.log_id = log_id;
	}
	public String getTicket_no() {
		return ticket_no;
	}
	public void setTicket_no(String string) {
		this.ticket_no = string;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getSubmitted_by() {
		return submited_by;
	}
	public void setSubmitted_by(String submited_by) {
		this.submited_by = submited_by;
	}
	
	public Vector getVector()	{
		Vector v = new Vector();
		v.add(log_id);
		v.add(comment);
		v.add(date);
		v.add(submited_by);
		
		return v;
	}
	

}
