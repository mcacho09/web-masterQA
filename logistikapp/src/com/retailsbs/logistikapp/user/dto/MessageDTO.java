package com.retailsbs.logistikapp.user.dto;

import java.util.List;

import com.retailsbs.logistikapp.user.domain.Attachment;
import com.retailsbs.logistikapp.user.domain.Message;

public class MessageDTO extends Message {

	private Long user_from_id;
	private String user_from_name;
	private String user_from_image;
	private Long user_to_id;
	private String user_to_name;
	private String user_to_image;
	private List<Attachment> attachment_files;
	
	
	public Long getUser_from_id() {
		return user_from_id;
	}

	public void setUser_from_id(Long user_from_id) {
		this.user_from_id = user_from_id;
	}

	public String getUser_from_name() {
		return user_from_name;
	}

	public void setUser_from_name(String user_from_name) {
		this.user_from_name = user_from_name;
	}

	public String getUser_from_image() {
		return user_from_image;
	}

	public void setUser_from_image(String user_from_image) {
		this.user_from_image = user_from_image;
	}

	public Long getUser_to_id() {
		return user_to_id;
	}

	public void setUser_to_id(Long user_to_id) {
		this.user_to_id = user_to_id;
	}

	public String getUser_to_name() {
		return user_to_name;
	}

	public void setUser_to_name(String user_to_name) {
		this.user_to_name = user_to_name;
	}

	public String getUser_to_image() {
		return user_to_image;
	}

	public void setUser_to_image(String user_to_image) {
		this.user_to_image = user_to_image;
	}
	public List<Attachment> getAttachment_files() {
		return attachment_files;
	}

	public void setAttachment_files(List<Attachment> attachment_files) {
		this.attachment_files = attachment_files;
	}


}
