package com.policy.dto;

import java.util.Date;

public class PolicyResponseDto {
	private int policyId;
	private String policyNumber;
	private String policyType;
	private double premium;
	private Date transactionCommitDate;
	private String responseMessage;
	private String transactionType;
	
	
	public PolicyResponseDto() {
		super();
	}
	public PolicyResponseDto(int policyId, String policyNumber, String policyType, double premium,
			Date transactionCommitDate, String responseMessage, String transactionType) {
		super();
		this.policyId = policyId;
		this.policyNumber = policyNumber;
		this.policyType = policyType;
		this.premium = premium;
		this.transactionCommitDate = transactionCommitDate;
		this.responseMessage = responseMessage;
		this.transactionType = transactionType;
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
	public String getPolicyType() {
		return policyType;
	}
	public void setPolicyType(String policyType) {
		this.policyType = policyType;
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
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	@Override
	public String toString() {
		return "PolicyResponseDto [policyId=" + policyId + ", policyNumber=" + policyNumber + ", policyType="
				+ policyType + ", premium=" + premium + ", transactionCommitDate=" + transactionCommitDate
				+ ", responseMessage=" + responseMessage + ", transactionType=" + transactionType + "]";
	}
	
	
}
