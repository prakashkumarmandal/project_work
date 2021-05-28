package com.et.portal.controller;

import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.et.portal.entity.TranType;
import com.et.portal.model.ETUserModel;
import com.et.portal.model.TranModel;
import com.et.portal.model.TranSummary;
import com.et.portal.service.TranService;
import com.et.portal.service.UserService;

@Controller
@Scope("session")
public class UserController {
	
	private ETUserModel currentUser;

	@Autowired
	private UserService userService;
	
	@Autowired
	private TranService tranService;
	
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
	
	@PostMapping("/login")
	public ModelAndView login(@RequestParam("eid") String email,@RequestParam("pwd") String password) {
		ModelAndView mv = null;
		
		currentUser = userService.login(email, password);
		if(currentUser!=null) {
			mv = new ModelAndView("userDashboard","currentUser",currentUser);
		}else {
			mv = new ModelAndView("index","errMsg","Access Denied!");
		}
		
		return mv;
	}
	
	@RequestMapping("/userNav")
	public String userNavBar() {
		return "userNavBar";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		currentUser=null;
		session.invalidate();
		return "redirect:/home";
	}
	
	@ModelAttribute(name = "txnTypes")
	public TranType[] txnTypes() {
		return TranType.values();
	}
	
	@ModelAttribute(name="dtFormat")
	public DateTimeFormatter dtFormat() {
		return DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm");
	}
	
	@GetMapping("/plusTran")
	public ModelAndView newTransaction() {
		return new ModelAndView("plusTranPage", "tran",new TranModel());
	}
	
	@PostMapping("/plusTran")
	public ModelAndView addTransaction(@ModelAttribute("tran") @Valid TranModel tran,BindingResult valiResult) {
		ModelAndView mv =null;
		
		if(valiResult.hasErrors())
			mv = new ModelAndView("plusTranPage", "tran",tran);
		else {
			tran.setUser(currentUser);
			tranService.save(tran);
			mv = new ModelAndView("userDashboard","currentUser",currentUser);
		}
		return mv;
	}
	
	@GetMapping("/trans")
	public ModelAndView getTransactions() {
		List<TranModel> trans = tranService.getAllTransByUser(currentUser);
		TranSummary tranSummary = tranService.getTranSummary(trans);
		ModelAndView mv = new ModelAndView("transPage","trans",trans);
		mv.addObject("tranSummary",tranSummary);
		return mv;
	}
	
	@GetMapping("/delete/{txnId}")
	public ModelAndView deleteTransaction(@PathVariable("txnId")long txnId) {
		tranService.delete(txnId);
		return getTransactions();
	}
	
	@GetMapping("/edit/{txnId}")
	public ModelAndView editTransaction(@PathVariable("txnId")long txnId) {
		TranModel tran = tranService.getById(txnId);
		return new ModelAndView("editTranPage", "tran",tran);
	}
	
	@PostMapping("/edit/{txnId}")
	public ModelAndView doEditTransaction(@PathVariable("txnId")long txnId,@ModelAttribute("tran") @Valid TranModel tran,BindingResult valiResult) {
		ModelAndView mv =null;
		
		if(valiResult.hasErrors())
			mv = new ModelAndView("editTranPage", "tran",tran);
		else {
			tran.setUser(currentUser);
			tranService.save(tran,txnId);
			mv = getTransactions();
		}
		return mv;
	}
	
}
