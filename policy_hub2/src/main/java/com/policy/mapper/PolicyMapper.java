package com.policy.mapper;

import org.springframework.stereotype.Component;

import com.policy.dto.PolicyRequestDto;
import com.policy.dto.PolicyResponseDto;
import com.policy.entity.Policy;

@Component
public class PolicyMapper {

	
	public Policy convertPolicyRequestToPolicyEntity(PolicyRequestDto policyRequestDto) {
	 Policy policy = new Policy();
	 policy.setPolicyEffectiveDate(policyRequestDto.getPolicyEffectiveDate());
	 policy.setPolicyExpirationDate(policyRequestDto.getPolicyExpirationDate());
	 policy.setPolicyNumber(policyRequestDto.getPolicyNumber());
	 policy.setPolicyType(policyRequestDto.getPolicyType());
	 policy.setPremium(policyRequestDto.getPremium());
	 policy.setState(policyRequestDto.getState());
	 policy.setTransactionType(policyRequestDto.getTransactionType());
      return policy;
		
	}
	
	public PolicyResponseDto convertPolicyEntityToPolicyResponse(Policy policy) {
		PolicyResponseDto policyResponseDto = new PolicyResponseDto();
		policyResponseDto.setPolicyId(policy.getPolicyId());
		policyResponseDto.setPolicyNumber(policy.getPolicyNumber());
		policyResponseDto.setPolicyType(policy.getPolicyType());
		policyResponseDto.setPremium(policy.getPremium());
		policyResponseDto.setResponseMessage(policy.getResponseMessage());
		policyResponseDto.setTransactionCommitDate(policy.getTransactionCommitDate());
		policyResponseDto.setTransactionType(policy.getTransactionType());
		return policyResponseDto;
		
	}

}
