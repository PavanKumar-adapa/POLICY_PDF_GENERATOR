package com.policy.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.policy.dto.PdfResponseDto;
import com.policy.dto.PolicyRequestDto;
import com.policy.dto.PolicyResponseDto;
import com.policy.entity.Policy;
import com.policy.exceptionHandler.PdfGenerationException;
import com.policy.exceptionHandler.PolicyNotFoundException;
import com.policy.mapper.PolicyMapper;
import com.policy.pdfUtils.PdfGenerator;
import com.policy.repository.PolicyRepository;

@ExtendWith(MockitoExtension.class)
public class PolicyServiceTest {
	
	@InjectMocks
	private PolicyService policyService;
	
	@Mock
	private PolicyRepository policyRepository;
	
	@Mock
	private PolicyMapper policyMapper;
	
	@Mock
	private PdfGenerator pdfGenerator;
	
	@Test
	void savePolicySuccessSceanario() {
	      
		PolicyResponseDto policyResponseDto = new PolicyResponseDto();
		PolicyRequestDto policyRequestDto = new PolicyRequestDto();
		Policy policy = new Policy();
		Policy savedPolicy = new Policy();
		
		when(policyMapper.convertPolicyRequestToPolicyEntity(policyRequestDto)).thenReturn(policy);
		when(policyRepository.save(policy)).thenReturn(savedPolicy);
		when(policyMapper.convertPolicyEntityToPolicyResponse(policy)).thenReturn(policyResponseDto);
		
		PolicyResponseDto result = policyService.savePolicyIntoHub(policyRequestDto);
		
		assertEquals(policyResponseDto, result);
		verify(policyRepository).save(policy);
		verify(policyMapper).convertPolicyRequestToPolicyEntity(policyRequestDto);
		verify(policyMapper).convertPolicyEntityToPolicyResponse(policy);
	}
	
	@Test
	void savePolicyFailureSceanario() {
	      
		PolicyRequestDto policyRequestDto = new PolicyRequestDto();
		Policy policy = new Policy();
		
		when(policyMapper.convertPolicyRequestToPolicyEntity(policyRequestDto)).thenReturn(policy);
		when(policyRepository.save(policy)).thenThrow(new RuntimeException("Hey data base is not availble this time"));

	    assertThrows(RuntimeException.class, ()-> policyService.savePolicyIntoHub(policyRequestDto));
	}
	
	@Test
	void findPolicyFailureScenario() {
		
		String policyNumber = "PC123645";
		
		when(policyRepository.findByPolicyNumber(policyNumber)).thenReturn(List.of());
		
		assertThrows(PolicyNotFoundException.class, ()-> policyService.getPolicyDetails(policyNumber));
	}
	
	@Test
	void findPolicySuccessScenario() throws IOException {
		
		String policyNumber = "PC123645";
		Policy policy = new Policy();
		policy.setPolicyNumber(policyNumber);
		
		List<Policy> policies = List.of(policy);
		
		PdfResponseDto pdfResponseDto = new PdfResponseDto();
		pdfResponseDto.setMessage("Pdf generated successfully");
		
		when(policyRepository.findByPolicyNumber(policyNumber)).thenReturn(policies);
		when(pdfGenerator.generatePdf(policies)).thenReturn(pdfResponseDto);

		PdfResponseDto result = policyService.getPolicyDetails(policyNumber);
		assertEquals(pdfResponseDto, result);
		verify(policyRepository).findByPolicyNumber(policyNumber);
		verify(pdfGenerator).generatePdf(policies);
		
	}

	@Test
	void generatePdfFailureScenario() throws IOException {
		
		String policyNumber = "PC123645";
		Policy policy = new Policy();
		policy.setPolicyNumber(policyNumber);
		
		List<Policy> policies = List.of(policy);
		
		PdfResponseDto pdfResponseDto = new PdfResponseDto();
		pdfResponseDto.setMessage("Pdf generated successfully");
		
		when(policyRepository.findByPolicyNumber(policyNumber)).thenReturn(policies);
		when(pdfGenerator.generatePdf(policies)).thenThrow(new PdfGenerationException("pdf generator is not availble this time"));

		assertThrows(PdfGenerationException.class, () -> policyService.getPolicyDetails(policyNumber));
		verify(policyRepository).findByPolicyNumber(policyNumber);
		verify(pdfGenerator).generatePdf(policies);
		
	}
	
	

}
