package com.retailsbs.logistikapp.user.exception;

public class LoginUserDuplicateException extends Exception {

	private static final long serialVersionUID = 1L;

	public LoginUserDuplicateException(String msg) {
		super(msg);
	}

}
