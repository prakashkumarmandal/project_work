package com.et.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.et.api.exception.InvalidUserDetailsException;
import com.et.api.model.ETUserModel;
import com.et.api.service.UserService;

@RestController
public class CommonsController {
	
	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ResponseEntity<ETUserModel> registerUser
		(@RequestBody @Valid ETUserModel user,BindingResult validResult) throws InvalidUserDetailsException {
		
		if(validResult.hasErrors()) {
			throw new InvalidUserDetailsException(validResult);
		}
		
		user = userService.register(user);
		user.setPassword(null);
		return ResponseEntity.ok(user);
	}
}
