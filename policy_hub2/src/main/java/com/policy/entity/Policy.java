package com.policy.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Policy {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int policyId;
	private String policyNumber;
	private String state;
	private String policyType;
	private Date policyEffectiveDate;
	private Date policyExpirationDate;
	private String transactionType;
	private double premium;
	private Date transactionCommitDate;
	private String responseMessage;
	
	
	public Policy() {
		super();
	}

	public Policy(int policyId, String policyNumber, String state, String policyType, Date policyEffectiveDate,
			Date policyExpirationDate, String transactionType, double premium, Date transactionCommitDate,
			String responseMessage) {
		super();
		this.policyId = policyId;
		this.policyNumber = policyNumber;
		this.state = state;
		this.policyType = policyType;
		this.policyEffectiveDate = policyEffectiveDate;
		this.policyExpirationDate = policyExpirationDate;
		this.transactionType = transactionType;
		this.premium = premium;
		this.transactionCommitDate = transactionCommitDate;
		this.responseMessage = responseMessage;
	}
	
	public int getPolicyId() {
		return policyId;
	}
	public void setPolicyId(int policyId) {
		this.policyId = policyId;
	}
	public String getPolicyNumber() {
		return policyNumber;
	}
	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPolicyType() {
		return policyType;
	}
	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}
	public Date getPolicyEffectiveDate() {
		return policyEffectiveDate;
	}
	public void setPolicyEffectiveDate(Date policyEffectiveDate) {
		this.policyEffectiveDate = policyEffectiveDate;
	}
	public Date getPolicyExpirationDate() {
		return policyExpirationDate;
	}
	public void setPolicyExpirationDate(Date policyExpirationDate) {
		this.policyExpirationDate = policyExpirationDate;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public double getPremium() {
		return premium;
	}
	public void setPremium(double premium) {
		this.premium = premium;
	}
	public Date getTransactionCommitDate() {
		return transactionCommitDate;
	}
	public void setTransactionCommitDate(Date transactionCommitDate) {
		this.transactionCommitDate = transactionCommitDate;
	}
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	@Override
	public String toString() {
		return "Policy [policyId=" + policyId + ", policyNumber=" + policyNumber + ", state=" + state + ", policyType="
				+ policyType + ", policyEffectiveDate=" + policyEffectiveDate + ", policyExpirationDate="
				+ policyExpirationDate + ", transactionType=" + transactionType + ", premium=" + premium
				+ ", transactionCommitDate=" + transactionCommitDate + ", responseMessage=" + responseMessage + "]";
	}	

}
