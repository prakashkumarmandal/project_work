package com.et.api.exception;

import org.springframework.validation.BindingResult;

public class InvalidTxnDetailsException extends Exception{
	public InvalidTxnDetailsException(BindingResult res) {
		super(res.getAllErrors().stream()
				.map(err -> err.getDefaultMessage())
				.reduce((s1,s2) -> s1 + "," + s2)
				.orElse("Invalid User Details"));
	}
}
