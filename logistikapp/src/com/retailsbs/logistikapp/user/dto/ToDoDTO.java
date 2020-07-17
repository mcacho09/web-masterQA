package com.retailsbs.logistikapp.user.dto;

import com.retailsbs.logistikapp.user.domain.ToDo;

public class ToDoDTO extends ToDo{
	
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
