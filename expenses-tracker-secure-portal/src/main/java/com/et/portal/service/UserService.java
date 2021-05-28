package com.et.portal.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.et.portal.model.ETUserModel;

public interface UserService extends UserDetailsService{
	ETUserModel register(ETUserModel user);
	ETUserModel getUserByEmail(String emailId);
}
