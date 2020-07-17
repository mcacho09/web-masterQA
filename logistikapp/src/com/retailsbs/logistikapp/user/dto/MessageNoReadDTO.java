package com.retailsbs.logistikapp.user.dto;

import java.util.Date;

public class MessageNoReadDTO {

	private Long id_user_message;
	private Long id_user;
	private String username;
	private String image;
	private String message;
	private Date created;
	private Integer qty;

	public Long getId_user_message() {
		return id_user_message;
	}

	public void setId_user_message(Long id_user_message) {
		this.id_user_message = id_user_message;
	}

	public Long getId_user() {
		return id_user;
	}

	public void setId_user(Long id_user) {
		this.id_user = id_user;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

}
