package com.policy.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.policy.dto.PdfResponseDto;
import com.policy.dto.PolicyRequestDto;
import com.policy.dto.PolicyResponseDto;
import com.policy.entity.Policy;
import com.policy.exceptionHandler.PolicyNotFoundException;
import com.policy.mapper.PolicyMapper;
import com.policy.pdfUtils.PdfGenerator;
import com.policy.repository.PolicyRepository;

@Service
public class PolicyService {
	
	@Autowired
	private PolicyMapper policyMapper;
	@Autowired
	private PolicyRepository policyRepository;
	@Autowired
	private PdfGenerator pdfGenerator;
	
	
	public PolicyResponseDto savePolicyIntoHub(PolicyRequestDto policyRequestDto) {
		Policy policy = policyMapper.convertPolicyRequestToPolicyEntity(policyRequestDto);
		policy.setTransactionCommitDate(new Date());
		policy.setResponseMessage("SUCESS");
		policyRepository.save(policy);
		PolicyResponseDto policyResponseDto = policyMapper.convertPolicyEntityToPolicyResponse(policy);
		return policyResponseDto;
	}
	public PdfResponseDto getPolicyDetails(String policyNumber) throws IOException {
		List<Policy> policies = policyRepository.findByPolicyNumber(policyNumber);
		if(policies.isEmpty()) {
			throw new PolicyNotFoundException("Hey no transactions found for policy " +policyNumber);
		}
		return pdfGenerator.generatePdf(policies);
	}
	
}
