package com.retailsbs.logistikapp.manager.user.dto;

public class AutoRegistroDTO {

	private String username;
	private String userlogin;
	private String passwd;
	private String email;
	private String name;
	private long id_plan;
	private String phone1;
	
	
	public long getId_plan() {
		return id_plan;
	}
	public void setId_plan(long id_plan) {
		this.id_plan = id_plan;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserlogin() {
		return userlogin;
	}
	public void setUserlogin(String userlogin) {
		this.userlogin = userlogin;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone1() {
		return phone1;
	}
	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}
	
}
