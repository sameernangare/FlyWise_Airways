package com.flywise.exception;


@SuppressWarnings("serial")
public class UserException extends Exception {

	public UserException() {
		super();
	}

	public UserException(String message) {
		super(message);
	}
}
