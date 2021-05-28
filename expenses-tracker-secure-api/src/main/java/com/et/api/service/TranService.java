package com.et.api.service;

import java.util.List;

import javax.validation.Valid;

import com.et.api.model.ETUserModel;
import com.et.api.model.TranModel;
import com.et.api.model.TranSummary;

public interface TranService {

	TranModel save(TranModel tran);
	TranModel save(@Valid TranModel tran, long txnId);	
	List<TranModel> getAllTransByUser(ETUserModel model);
	double getNetTranBalance(List<TranModel> txns);	
	TranSummary getTranSummary(List<TranModel> txns);
	void delete(long txnId);
	TranModel getById(long txnId);
	
}
