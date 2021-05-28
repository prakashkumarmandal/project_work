package com.et.portal.model;

public class TranSummary {

	private double credit;
	private double debit;
	private double balance;
	
	public TranSummary() {
		// TODO Auto-generated constructor stub
	}

	public TranSummary(double credit, double debit, double balance) {
		super();
		this.credit = credit;
		this.debit = debit;
		this.balance = balance;
	}

	public double getCredit() {
		return credit;
	}

	public void setCredit(double credit) {
		this.credit = credit;
	}

	public double getDebit() {
		return debit;
	}

	public void setDebit(double debit) {
		this.debit = debit;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	
}
