package com.et.portal.service;

import java.util.List;

import javax.validation.Valid;

import com.et.portal.model.ETUserModel;
import com.et.portal.model.TranModel;
import com.et.portal.model.TranSummary;

public interface TranService {

	TranModel save(TranModel tran);
	TranModel save(@Valid TranModel tran, long txnId);	
	List<TranModel> getAllTransByUser(ETUserModel model);
	double getNetTranBalance(List<TranModel> txns);	
	TranSummary getTranSummary(List<TranModel> txns);
	void delete(long txnId);
	TranModel getById(long txnId);
	
}
