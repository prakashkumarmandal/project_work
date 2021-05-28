package com.et.portal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.et.portal.dao.UserRepository;
import com.et.portal.entity.ETUserEntity;
import com.et.portal.model.ETUserModel;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repo;

	@Autowired
	private EMParser parser;

	@Autowired
	private PasswordEncoder pwdEncoder;

	@Override
	public ETUserModel register(ETUserModel user) {
		user.setPassword(pwdEncoder.encode(user.getPassword()));
		return parser.parse(repo.save(parser.parse(user)));
	}

	@Override
	public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {

		if (!repo.existsByEmailId(emailId)) {
			throw new UsernameNotFoundException("User with emailId: " + emailId + ", was not found in the database");
		}

		ETUserEntity appUser = repo.findByEmailId(emailId);

		List<GrantedAuthority> grantList = new ArrayList<>(); //list of roles..!

		return new User(appUser.getEmailId(), appUser.getPassword(), grantList);
	}

	@Override
	public ETUserModel getUserByEmail(String emailId) {
		return parser.parse(repo.findByEmailId(emailId));
	}

}
