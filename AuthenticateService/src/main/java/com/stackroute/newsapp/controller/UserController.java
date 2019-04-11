package com.stackroute.newsapp.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.stackroute.newsapp.model.User;
import com.stackroute.newsapp.services.SecurityTokenGenerator;
import com.stackroute.newsapp.services.UserService;

@Controller
@EnableWebMvc
@CrossOrigin("*")
@RequestMapping("api/userservice")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SecurityTokenGenerator tokenGenerator;
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody User user){
		try {
			userService.saveUser(user);
			return new ResponseEntity<String>("User registered sucessfully", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>("{\"message\":\""+e.getMessage()+"\"}", HttpStatus.CONFLICT);
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody User loginDetail){
		try {
			String userId = loginDetail.getUserId();
			String password = loginDetail.getPassword();
			
			if(userId==null || password==null) {
				throw new Exception("Username or password cannot be empty");
			}
			User user = userService.findByUserIdAndPassword(userId, password);
			if(user == null) {
				throw new Exception("User with given ID does not exists");
			}
			String pwd = user.getPassword();
			if(!password.equals(pwd)) {
				throw new Exception("Invalid Username or password");
			}
			Map<String, String> map = tokenGenerator.generateToken(user);
			return new ResponseEntity<Map<String,String>>(map, HttpStatus.OK);	
		} catch(Exception e) {
			return new ResponseEntity<String>("{\"message\":\""+e.getMessage()+"\"}", HttpStatus.UNAUTHORIZED);
		}
	}
	
	@GetMapping("/usertype/{userId}")
	public ResponseEntity<?> getUserType(@PathVariable("userId") String userId, HttpServletRequest request){
		String userType;
		User user;
		try {
			user = userService.findByUserId(userId);
			userType = user.getUserType();
		}catch(Exception e) {
			return new ResponseEntity<String>("Usertype not found",HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>(userType, HttpStatus.OK);
	}

}
