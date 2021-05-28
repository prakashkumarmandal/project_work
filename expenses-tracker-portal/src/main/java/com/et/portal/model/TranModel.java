package com.et.portal.model;

import java.time.LocalDateTime;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.et.portal.entity.TranType;

public class TranModel {

	private Long txnId;	
	
	@NotBlank(message="Header is a mandate field")
	@NotEmpty(message="Header is a mandate field")
	private String header;
	@Min(value = 1,message = "Amount cna not be negative or zero")
	private Double amount;
	@PastOrPresent(message = "Can not record future transactions")
	@DateTimeFormat(iso=ISO.DATE_TIME)
	private LocalDateTime dateAndTime;
	@NotNull(message="tranType is mandate")
	private TranType tranType;
	
	private ETUserModel user;
	
	public TranModel() {
		// TODO Auto-generated constructor stub
	}

	public TranModel(Long txnId, String header, Double amount, LocalDateTime dateAndTime,TranType tranType, ETUserModel user) {
		super();
		this.txnId = txnId;
		this.header = header;
		this.amount = amount;
		this.dateAndTime = dateAndTime;
		this.tranType=tranType;
		this.user = user;
	}

	public Long getTxnId() {
		return txnId;
	}

	public void setTxnId(Long txnId) {
		this.txnId = txnId;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public LocalDateTime getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(LocalDateTime dateAndTime) {
		this.dateAndTime = dateAndTime;
	}

	
	public TranType getTranType() {
		return tranType;
	}

	public void setTranType(TranType tranType) {
		this.tranType = tranType;
	}

	public ETUserModel getUser() {
		return user;
	}

	public void setUser(ETUserModel user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((dateAndTime == null) ? 0 : dateAndTime.hashCode());
		result = prime * result + ((header == null) ? 0 : header.hashCode());
		result = prime * result + ((txnId == null) ? 0 : txnId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TranModel other = (TranModel) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (dateAndTime == null) {
			if (other.dateAndTime != null)
				return false;
		} else if (!dateAndTime.equals(other.dateAndTime))
			return false;
		if (header == null) {
			if (other.header != null)
				return false;
		} else if (!header.equals(other.header))
			return false;
		if (txnId == null) {
			if (other.txnId != null)
				return false;
		} else if (!txnId.equals(other.txnId))
			return false;
		return true;
	}
	
	
}
