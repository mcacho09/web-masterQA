package com.retailsbs.logistikapp.user.dto;

import java.util.List;


public class SendMessageDTO {

	private Long id_user_message;
	private Long id_user;
	private Long id_user_to;
	private String message;
	private List<AttachmentDTO> attachment_files;
	
	

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

	public Long getId_user_to() {
		return id_user_to;
	}

	public void setId_user_to(Long id_user_to) {
		this.id_user_to = id_user_to;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<AttachmentDTO> getAttachment_files() {
		return attachment_files;
	}

	public void setAttachment_files(List<AttachmentDTO> attachment_files) {
		this.attachment_files = attachment_files;
	}
	
}
