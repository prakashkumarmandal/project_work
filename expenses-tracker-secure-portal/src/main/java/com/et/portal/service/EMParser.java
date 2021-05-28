package com.et.portal.service;

import org.springframework.stereotype.Service;

import com.et.portal.entity.ETUserEntity;
import com.et.portal.entity.TranEntity;
import com.et.portal.model.ETUserModel;
import com.et.portal.model.TranModel;

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
