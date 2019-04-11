package com.stackroute.newsapp.execeptions;

@SuppressWarnings("serial")
public class UserAlreadyExsistsException extends Exception {
	public UserAlreadyExsistsException(String message) {
		super(message);
	}
}
