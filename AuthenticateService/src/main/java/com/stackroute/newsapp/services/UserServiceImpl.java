package com.stackroute.newsapp.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.newsapp.execeptions.UserAlreadyExsistsException;
import com.stackroute.newsapp.execeptions.UserNotFoundException;
import com.stackroute.newsapp.model.User;
import com.stackroute.newsapp.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private final UserRepository userrepo;

	public UserServiceImpl(UserRepository userrepo) {
		super();
		this.userrepo = userrepo;
	}

	@Override
	public boolean saveUser(User user) throws UserAlreadyExsistsException{
		Optional<User> u = userrepo.findById(user.getUserId());
		if(u.isPresent()) {
			throw new UserAlreadyExsistsException("User with Id Already Exists");
		}
		userrepo.save(user);
		return true;
	}

	@Override
	public User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException {
		User user = userrepo.findByUserIdAndPassword(userId, password);
		if(user== null) {
			throw new UserNotFoundException("UserId or Password mismatch");
		}
		return user;
	}

	@Override
	public User findByUserId(String userId) throws UserNotFoundException {
		User user = userrepo.findByUserId(userId);
		if(user== null) {
			throw new UserNotFoundException("UserId or Password mismatch");
		}
		return user;
	}

}
