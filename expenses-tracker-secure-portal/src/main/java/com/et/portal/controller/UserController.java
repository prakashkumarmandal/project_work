package com.et.portal.controller;

import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.et.portal.entity.TranType;
import com.et.portal.model.ETUserModel;
import com.et.portal.model.TranModel;
import com.et.portal.model.TranSummary;
import com.et.portal.service.TranService;
import com.et.portal.service.UserService;

@Controller
@Scope("session")
@RequestMapping("/users")
public class UserController {
	
	private ETUserModel currentUser;

	@Autowired
	private UserService userService;
	
	@Autowired
	private TranService tranService;
	
	@RequestMapping("/nav")
	public String userNavBar() {
		return "users/nav";
	}
	
	@GetMapping("/dashboard")
	public ModelAndView getDashBoard() {
		ModelAndView mv = new ModelAndView("users/dashboard");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(!(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) {
			String emailId = auth.getName();
			currentUser = userService.getUserByEmail(emailId);
			mv.addObject("currentUser",currentUser);
		}else {
			mv = new ModelAndView("redirect:/home");
		}
		return mv; 
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
		return new ModelAndView("users/plusTranPage", "tran",new TranModel());
	}
	
	@PostMapping("/plusTran")
	public ModelAndView addTransaction(@ModelAttribute("tran") @Valid TranModel tran,BindingResult valiResult) {
		ModelAndView mv =null;
		
		if(valiResult.hasErrors())
			mv = new ModelAndView("users/plusTranPage", "tran",tran);
		else {
			tran.setUser(currentUser);
			tranService.save(tran);
			mv = new ModelAndView("redirect:/users/dashboard");
		}
		return mv;
	}
	
	@GetMapping("/trans")
	public ModelAndView getTransactions() {
		List<TranModel> trans = tranService.getAllTransByUser(currentUser);
		TranSummary tranSummary = tranService.getTranSummary(trans);
		
		ModelAndView mv = new ModelAndView("users/transPage","trans",trans);
		
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
		return new ModelAndView("users/editTranPage", "tran",tran);
	}
	
	@PostMapping("/edit/{txnId}")
	public ModelAndView doEditTransaction(@PathVariable("txnId")long txnId,@ModelAttribute("tran") @Valid TranModel tran,BindingResult valiResult) {
		ModelAndView mv =null;
		
		if(valiResult.hasErrors())
			mv = new ModelAndView("users/editTranPage", "tran",tran);
		else {
			tran.setUser(currentUser);
			tranService.save(tran,txnId);
			mv = getTransactions();
		}
		return mv;
	}
	
}
