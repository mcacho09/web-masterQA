package com.retailsbs.logistikapp.user.dto;

import com.retailsbs.logistikapp.user.domain.Notification;

public class NotificationDTO extends Notification{

	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
