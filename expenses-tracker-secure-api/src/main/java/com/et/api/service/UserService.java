package com.et.api.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.et.api.model.ETUserModel;

public interface UserService extends UserDetailsService{
	ETUserModel register(ETUserModel user);
	ETUserModel getUserByEmail(String emailId);
	ETUserModel getUserById(int userId);
}
