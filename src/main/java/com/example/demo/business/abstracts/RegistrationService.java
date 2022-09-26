package com.example.demo.business.abstracts;

import java.util.List;

import com.example.demo.entities.User;

public interface RegistrationService {
	List<User> listOfUsers();
	void registrateUser(User user);
	String verification(User user);
	
}
