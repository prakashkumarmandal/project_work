package com.et.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.et.api.exception.InvalidTxnDetailsException;
import com.et.api.model.TranModel;
import com.et.api.service.TranService;

@RestController
@RequestMapping("/txns")
public class TxnController {
		
	@Autowired
	private TranService tranService;
		
	@PutMapping("/{txnId}")
	public ResponseEntity<TranModel> addTransaction(@PathVariable("txnId")long txnId,@RequestBody @Valid TranModel tran,BindingResult valiResult) throws InvalidTxnDetailsException {
		
		if(valiResult.hasErrors()){
			throw new InvalidTxnDetailsException(valiResult);
		}
		
		tran = tranService.save(tran, txnId);
		
		return ResponseEntity.ok(tran);
	}
	
	@DeleteMapping("/{txnId}")
	public ResponseEntity<Void> deleteTransactions(@PathVariable("txnId")long txnId) {
		tranService.delete(txnId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
