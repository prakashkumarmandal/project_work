package com.et.portal.service;

import com.et.portal.model.ETUserModel;

public interface UserService {
	ETUserModel register(ETUserModel user);
	ETUserModel login(String emailId,String password);
}
