package com.et.api.service;

import com.et.api.model.ETUserModel;

public interface UserService /*extends UserDetailsService*/{
	ETUserModel register(ETUserModel user);
	ETUserModel getUserByEmail(String emailId);
	ETUserModel getUserById(int userId);
}
