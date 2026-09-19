package com.policy.dto;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class PolicyRequestDto {
	@NotBlank(message ="Policy number should not be blank")
	private String policyNumber;
	@NotBlank(message ="State should be not blank")
	private String state;
	@NotBlank(message ="policyType should not  be blank")
	private String policyType;
	private Date policyEffectiveDate;
	private Date policyExpirationDate;
	@NotBlank(message ="Transaction Type should not be blank")
	private String transactionType;
	@Positive(message ="Premium should be greater than zero")
	private double premium;
	
	
	
	public PolicyRequestDto() {
		super();
	}
	
	public PolicyRequestDto(String policyNumber, String state, String policyType, Date policyEffectiveDate,
			Date policyExpirationDate, String transactionType, double premium) {
		super();
		this.policyNumber = policyNumber;
		this.state = state;
		this.policyType = policyType;
		this.policyEffectiveDate = policyEffectiveDate;
		this.policyExpirationDate = policyExpirationDate;
		this.transactionType = transactionType;
		this.premium = premium;
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
	
	@Override
	public String toString() {
		return "PolicyRequestDto [policyNumber=" + policyNumber + ", state=" + state + ", policyType=" + policyType
				+ ", policyEffectiveDate=" + policyEffectiveDate + ", policyExpirationDate=" + policyExpirationDate
				+ ", transactionType=" + transactionType + ", premium=" + premium + "]";
	}
	
	

}
