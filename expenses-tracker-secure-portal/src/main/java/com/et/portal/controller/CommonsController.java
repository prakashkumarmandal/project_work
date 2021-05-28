package com.et.portal.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.et.portal.model.ETUserModel;
import com.et.portal.service.UserService;

@Controller
public class CommonsController {
	
	@Autowired
	private UserService userService;

	@GetMapping({"","/","/home","/login"})
	public ModelAndView showHome() {
		return new ModelAndView("index");
	}
	
	@RequestMapping("/nav")
	public String navbar() {
		return "nav";
	}
	
	@GetMapping("/register")
	public ModelAndView registerForm() {
		return new ModelAndView("register", "user",new ETUserModel());
	}
	
	@PostMapping("/register")
	public ModelAndView registerUser(@ModelAttribute("user") @Valid ETUserModel user,BindingResult validResult) {
		
		ModelAndView mv=null;
		
		if(validResult.hasErrors())
			mv = new ModelAndView("register", "user",user);
		else {
			user = userService.register(user);
			mv = new ModelAndView("redirect:/home");
		}
		
		return mv;
	}
}
