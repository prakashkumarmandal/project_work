package com.et.portal.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="trans")
public class TranEntity implements Comparable<TranEntity>{

	@Id
	@GeneratedValue
	private Long txnId;
	
	private String header;
	private Double amount;
	private LocalDateTime dateAndTime;
	
	@Enumerated(EnumType.STRING)
	private TranType tranType;
	
	@ManyToOne
	private ETUserEntity user;
	
	public TranEntity() {
		// TODO Auto-generated constructor stub
	}

	public TranEntity(Long txnId, String header, Double amount, LocalDateTime dateAndTime,TranType tranType,ETUserEntity user) {
		super();
		this.txnId = txnId;
		this.header = header;
		this.amount = amount;
		this.dateAndTime = dateAndTime;
		this.tranType=tranType;
		this.user=user;
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

	public ETUserEntity getUser() {
		return user;
	}

	public void setUser(ETUserEntity user) {
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
		TranEntity other = (TranEntity) obj;
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

	@Override
	public int compareTo(TranEntity arg0) {
		return this.txnId.compareTo(arg0.txnId);
	}
	
	
}
