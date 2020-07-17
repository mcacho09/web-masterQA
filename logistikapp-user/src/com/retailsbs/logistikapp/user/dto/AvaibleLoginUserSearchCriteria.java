package com.retailsbs.logistikapp.user.dto;

public class AvaibleLoginUserSearchCriteria {

	private Long id_user;
	private String active;
	private String userlogin;

	public Long getId_user() {
		return id_user;
	}
	public void setId_user(Long id_user) {
		this.id_user = id_user;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public String getUserlogin() {
		return userlogin;
	}
	public void setUserlogin(String userlogin) {
		this.userlogin = userlogin;
	}
	
}
