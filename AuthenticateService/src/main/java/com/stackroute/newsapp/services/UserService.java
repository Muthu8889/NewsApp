package com.stackroute.newsapp.services;

import com.stackroute.newsapp.execeptions.UserAlreadyExsistsException;
import com.stackroute.newsapp.execeptions.UserNotFoundException;
import com.stackroute.newsapp.model.User;

public interface UserService {
	
	boolean saveUser(User user) throws UserAlreadyExsistsException, UserNotFoundException;

	public User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException;
	
	public User findByUserId(String userId) throws UserNotFoundException;
}
