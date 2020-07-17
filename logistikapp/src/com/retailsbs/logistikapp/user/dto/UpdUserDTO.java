package com.retailsbs.logistikapp.user.dto;

import com.retailsbs.logistikapp.user.domain.User;

public class UpdUserDTO extends User {
	
	private String newPasswd;
	private String lastPasswd;
	private String verifiedPasswd;

	public String getNewPasswd() {
		return newPasswd;
	}
	public void setNewPasswd(String newPasswd) {
		this.newPasswd = newPasswd;
	}
	public String getLastPasswd() {
		return lastPasswd;
	}
	public void setLastPasswd(String lastPasswd) {
		this.lastPasswd = lastPasswd;
	}
	public String getVerifiedPasswd() {
		return verifiedPasswd;
	}
	public void setVerifiedPasswd(String verifiedPasswd) {
		this.verifiedPasswd = verifiedPasswd;
	}

}
