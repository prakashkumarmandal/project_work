package com.et.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.et.api.exception.InvalidTxnDetailsException;
import com.et.api.model.TranModel;
import com.et.api.service.TranService;
import com.et.api.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TranService tranService;
		
	@PostMapping("/{userId}/trans")
	public ResponseEntity<TranModel> addTransaction(@PathVariable("userId")int userId,@RequestBody @Valid TranModel tran,BindingResult valiResult) throws InvalidTxnDetailsException {
		
		if(valiResult.hasErrors()){
			throw new InvalidTxnDetailsException(valiResult);
		}
		
		tran.setUser(userService.getUserById(userId));
		tran = tranService.save(tran);
		
		return ResponseEntity.ok(tran);
	}
	
	@GetMapping("/{userId}/trans")
	public ResponseEntity<List<TranModel>> getTransactions(@PathVariable("userId")int userId) {
		List<TranModel> trans = tranService.getAllTransByUser(userService.getUserById(userId));
		return ResponseEntity.ok(trans);
	}
}
