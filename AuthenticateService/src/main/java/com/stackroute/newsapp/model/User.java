package com.stackroute.newsapp.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class User {

	@Id
	private String userId;
	private String firstname;
	private String lastname;
	private String password;
	private String userType;
	@CreationTimestamp
	private Date created;
	
	public User() {
		super();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public User(String userId, String firstname, String lastname, String password, String userType, Date created) {
		super();
		this.userId = userId;
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
		this.userType = userType;
		this.created = created;
	}

	
}
