package com.stackroute.newsapp.execeptions;

@SuppressWarnings("serial")
public class UserNotFoundException extends Exception{
	public UserNotFoundException(String message) {
		super(message);
	}
}
