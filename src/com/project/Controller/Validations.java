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
}
