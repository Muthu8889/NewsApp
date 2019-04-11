package com.stackroute.newsapp.services;

import java.util.Map;

import com.stackroute.newsapp.model.User;

public interface SecurityTokenGenerator {
	Map<String ,String> generateToken(User user);
}
