package com.retailsbs.logistikapp.user.exception;

public class EmailUserDuplicateException extends Exception {

	private static final long serialVersionUID = 1L;

	public EmailUserDuplicateException(String msg) {
		super(msg);
	}
	
}
