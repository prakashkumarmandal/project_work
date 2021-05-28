package com.et.api.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.et.api.dao.TranRepository;
import com.et.api.entity.ETUserEntity;
import com.et.api.entity.TranType;
import com.et.api.model.ETUserModel;
import com.et.api.model.TranModel;
import com.et.api.model.TranSummary;

@Service
public class TranServicImpl implements TranService {
	@Autowired
	private TranRepository repo;

	@Autowired
	private EMParser parser;

	@Override
	public TranModel save(TranModel tran) {
		return parser.parse(repo.save(parser.parse(tran)));
	}

	@Override
	public TranModel save(@Valid TranModel tran, long txnId) {
		if(txnId!=tran.getTxnId())
			throw new RuntimeException("Unintended update encountered");
		return parser.parse(repo.save(parser.parse(tran)));
	}
	
	@Override
	public List<TranModel> getAllTransByUser(ETUserModel model) {
		return repo.findAllByUser(parser.parse(model)).stream().map(parser::parse).collect(Collectors.toList());
	}

	@Override
	public double getNetTranBalance(List<TranModel> txns) {
		double balance=0;
		
		if(txns!=null) {
			balance = txns.stream()
					.map(t -> t.getTranType()==TranType.INCOME?t.getAmount():-t.getAmount())
					.reduce((a1,a2) -> a1+a2).orElse(new Double(0));
		}
		
		return balance;
	}
	
	private double getNetByType(List<TranModel> txns,TranType type) {
		return txns==null?0:txns.stream()
				.filter(t -> t.getTranType()==type)
				.map(TranModel::getAmount)
				.reduce((a1,a2) -> a1+a2).orElse(new Double(0));
	}

	@Override
	public TranSummary getTranSummary(List<TranModel> txns) {
		TranSummary result = null;
		
		if(txns!=null) {
			double credit =getNetByType(txns, TranType.INCOME);
			double debit = getNetByType(txns, TranType.EXPENSE);
			result = new TranSummary(credit,debit,credit-debit);
		}
		
		return result;
	}

	@Override
	public void delete(long txnId) {
		repo.deleteById(txnId);
	}

	@Override
	public TranModel getById(long txnId) {
		return parser.parse(repo.findById(txnId).orElse(null));
	}

	
}
