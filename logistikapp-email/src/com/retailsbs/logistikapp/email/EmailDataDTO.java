package com.retailsbs.logistikapp.email;

import java.util.Date;

public class EmailDataDTO {
	
	private String email;
	private String userlogin;
	private String passwd;
	private String name;
	private String plan_name;
	private Date plan_started;
	private Date plan_end;
	private String username;
	private String subject;
	private String message;
	private String from;
	private String to;
	private String bcc;
	private String name_from;
	private long idPlan;
	private long customers;
	private long users;
	private String phone;
	private long idMessage;
	private String profile;
	
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public long getIdMessage() {
		return idMessage;
	}
	public void setIdMessage(long idMessage) {
		this.idMessage = idMessage;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPlan_name() {
		return plan_name;
	}
	public void setPlan_name(String plan_name) {
		this.plan_name = plan_name;
	}
	public Date getPlan_started() {
		return plan_started;
	}
	public void setPlan_started(Date plan_started) {
		this.plan_started = plan_started;
	}
	public Date getPlan_end() {
		return plan_end;
	}
	public void setPlan_end(Date plan_end) {
		this.plan_end = plan_end;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getBcc() {
		return bcc;
	}
	public void setBcc(String bcc) {
		this.bcc = bcc;
	}
	public String getName_from() {
		return name_from;
	}
	public void setName_from(String name_from) {
		this.name_from = name_from;
	}
	public long getIdPlan() {
		return idPlan;
	}
	public void setIdPlan(long idPlan) {
		this.idPlan = idPlan;
	}
	public long getCustomers() {
		return customers;
	}
	public void setCustomers(long customers) {
		this.customers = customers;
	}
	public long getUsers() {
		return users;
	}
	public void setUsers(long users) {
		this.users = users;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	
	
}
