package com.et.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.et.api.exception.InvalidUserDetailsException;
import com.et.api.model.ETUserModel;
import com.et.api.model.JwtToken;
import com.et.api.service.UserService;
import com.et.api.util.JwtTokenUtil;

@RestController
public class CommonsController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@PostMapping("/login")
	public ResponseEntity<JwtToken> createAuthenticationToken(@RequestBody ETUserModel user) throws Exception {
		authenticate(user.getEmailId(), user.getPassword());
		final UserDetails userDetails = userService.loadUserByUsername(user.getEmailId());
		final String token = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new JwtToken(token));
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

	@PostMapping("/register")
	public ResponseEntity<ETUserModel> registerUser
		(@RequestBody @Valid ETUserModel user,BindingResult validResult) throws InvalidUserDetailsException {
		
		if(validResult.hasErrors()) {
			throw new InvalidUserDetailsException(validResult);
		}
		
		user = userService.register(user);
	
		return ResponseEntity.ok(user);
	}
}
