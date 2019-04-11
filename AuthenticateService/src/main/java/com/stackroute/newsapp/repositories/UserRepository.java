package com.stackroute.newsapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stackroute.newsapp.model.User;

public interface UserRepository extends JpaRepository<User, String> {
	
	User findByUserIdAndPassword(String userId, String password);
	
	User findByUserId(String userId);

}
