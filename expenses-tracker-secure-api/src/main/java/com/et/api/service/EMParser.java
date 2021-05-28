package com.et.api.service;

import org.springframework.stereotype.Service;

import com.et.api.entity.ETUserEntity;
import com.et.api.entity.TranEntity;
import com.et.api.model.ETUserModel;
import com.et.api.model.TranModel;

@Service
public class EMParser {

	public ETUserEntity parse(ETUserModel source) {
		return source==null?null:
			new ETUserEntity(source.getUserId(), source.getUserName(), source.getEmailId(), source.getPassword());
	}
	
	public ETUserModel parse(ETUserEntity source) {
		return source==null?null:
			new ETUserModel(source.getUserId(), source.getUserName(), source.getEmailId(), source.getPassword());
	}
	
	public TranEntity parse(TranModel source) {
		return new TranEntity(
				source.getTxnId(),source.getHeader(),source.getAmount(),source.getDateAndTime(),
				source.getTranType(),parse(source.getUser()));
	}

	public TranModel parse(TranEntity source) {
		return new TranModel(
				source.getTxnId(),source.getHeader(),source.getAmount(),source.getDateAndTime(),
				source.getTranType(),parse(source.getUser()));
	}
}
