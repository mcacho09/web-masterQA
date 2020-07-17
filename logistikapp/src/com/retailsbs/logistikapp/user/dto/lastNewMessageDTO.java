package com.retailsbs.logistikapp.user.dto;

import com.retailsbs.logistikapp.user.domain.Message;

public class lastNewMessageDTO extends Message{
	
	private String image_sender;
	private String username_sender;
	private String profile_sender;

	public String getUsername_sender() {
		return username_sender;
	}

	public void setUsername_sender(String username_sender) {
		this.username_sender = username_sender;
	}

	public String getProfile_sender() {
		return profile_sender;
	}

	public void setProfile_sender(String profile_sender) {
		this.profile_sender = profile_sender;
	}

	public String getImage_sender() {
		return image_sender;
	}

	public void setImage_sender(String image_sender) {
		this.image_sender = image_sender;
	}

}
