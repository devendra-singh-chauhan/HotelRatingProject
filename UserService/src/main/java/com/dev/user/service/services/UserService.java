package com.dev.user.service.services;

import java.util.List;

import com.dev.user.service.entities.User;

public interface UserService {

	//create user
	User saveUser(User user);
	
	//get all user
	List<User> getAllUser();
	
	//get single user
	User getUser(String userId);
	
	//delete
	
	//update
}
