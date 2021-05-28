package com.et.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CommonsController {

	@GetMapping({"","/","/home"})
	public ModelAndView showHome() {
		return new ModelAndView("index");
	}
	
	@RequestMapping("/nav")
	public String navbar() {
		return "nav";
	}
}
