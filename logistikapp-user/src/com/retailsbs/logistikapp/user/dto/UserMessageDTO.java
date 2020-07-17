package com.retailsbs.logistikapp.user.dto;

import java.util.Date;

public class UserMessageDTO {

	private Long id_user_message;
	private String username;
	private Integer qty;
	private Date since;
	private int attachments;
	private String message;
	private String read;


	public Long getId_user_message() {
		return id_user_message;
	}

	public void setId_user_message(Long id_user_message) {
		this.id_user_message = id_user_message;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public Date getSince() {
		return since;
	}

	public void setSince(Date since) {
		this.since = since;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getRead() {
		return read;
	}

	public void setRead(String read) {
		this.read = read;
	}
	public int getAttachments() {
		return attachments;
	}
	public void setAttachments(int attachments) {
		this.attachments = attachments;
	}

}
