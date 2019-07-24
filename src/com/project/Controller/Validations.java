package com.project.Controller;


public class Validations {
	
	public boolean validateEmail(String email) {
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
	    return email.matches(regex);
	}
	
	public boolean validatePhone(String phone) {
		String regex = "^[0-9]{10}$";
		return phone.matches(regex);
	}
	
	public boolean isNotEmpty(String input) {
		if(input.isEmpty()) {
			return false;
		}
		else
			return true;
	}
	
	public boolean validateTicketNum(String Tnum) {
		String regex = "^[0-9]*$";
		return Tnum.matches(regex);
	}
	
	public boolean validateDate(String Date) {
		String regex = "^[0-9]{4}/(1[0-2]|0[1-9])/(3[01]|[12][0-9]|0[1-9])$";
		return Date.matches(regex);
	}
}
