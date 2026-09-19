package com.policy.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.policy.dto.PdfResponseDto;
import com.policy.dto.PolicyRequestDto;
import com.policy.dto.PolicyResponseDto;
import com.policy.exceptionHandler.PdfGenerationException;
import com.policy.exceptionHandler.PolicyNotFoundException;
import com.policy.service.PolicyService;

import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;

@WebMvcTest(PolicyController.class)
public class PolicyControllerTest {
	
	@MockitoBean
	private PolicyService policyService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	ObjectMapper objectMapper;
	
	String policyNumber ="XC258963";
    
	@Test
	void savePolicyToDataBaseSuccessScenario() throws JacksonException, Exception {	
		
		PolicyRequestDto policyRequestDto = new PolicyRequestDto();
		policyRequestDto.setPolicyNumber("PC12345");
		policyRequestDto.setPolicyType("PKG");
		policyRequestDto.setPremium(25000);
		policyRequestDto.setState("NY");
		policyRequestDto.setTransactionType("NEW");
		
		PolicyResponseDto policyResponseDto = new PolicyResponseDto();
		policyResponseDto.setPolicyNumber("PC12345");
		policyResponseDto.setPolicyType("PKG");
		policyResponseDto.setPremium(25000);
		policyResponseDto.setResponseMessage("SUCCESS");
		policyResponseDto.setTransactionType("NEW");
		
		when(policyService.savePolicyIntoHub(any(PolicyRequestDto.class))).thenReturn(policyResponseDto);
		
		mockMvc.perform(
				post("/policy/savepolicy")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(policyRequestDto))
				).andExpect(status().isOk());	
	}
	@Test
	void savePolicyToDataBaseFailureScenario() throws JacksonException, Exception {
		
		PolicyRequestDto policyRequestDto = new PolicyRequestDto();
		policyRequestDto.setPolicyNumber(" ");
		policyRequestDto.setPolicyType("PKG");
		policyRequestDto.setPremium(25000);
		policyRequestDto.setState("NY");
		policyRequestDto.setTransactionType("NEW");
		policyRequestDto.setPolicyEffectiveDate(new SimpleDateFormat("yyyy-MM-dd").parse("2026-01-01"));
		policyRequestDto.setPolicyExpirationDate(new SimpleDateFormat("yyyy-MM-dd").parse("2027-01-01"));
		
		mockMvc.perform(
				post("/policy/savepolicy")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(policyRequestDto))
				).andExpect(status().isInternalServerError());
		verifyNoInteractions(policyService);
		
	}
	@Test
	void getThePolicyDetailsSuccessSceanrio() throws Exception {
		
		PdfResponseDto PdfResponseDto = new PdfResponseDto();
		PdfResponseDto.setMessage("SUCESSULLY GENERATED");
		
		when(policyService.getPolicyDetails(policyNumber)).thenReturn(PdfResponseDto);
		
		mockMvc.perform(
				get("/policy/getpolicy")
				.param("policyNumber", policyNumber)
				).andExpect(status().isOk());
		verify(policyService).getPolicyDetails(policyNumber);
		
	}
	@Test
	void getThePolicyDetailsFailureSceanrio() throws Exception {
		when(policyService.getPolicyDetails(policyNumber)).thenThrow( new PolicyNotFoundException("Hey policy not found"));
		
		mockMvc.perform(
				get("/policy/getpolicy")
				.param("policyNumber", policyNumber)
				).andExpect(status().isNotFound());
		verify(policyService).getPolicyDetails(policyNumber);
		
	}
	@Test
	void pdfGenerationFailureScenario() throws Exception {
    when(policyService.getPolicyDetails(policyNumber)).thenThrow( new PdfGenerationException("Hey pdf is having issues "));
		
		mockMvc.perform(
				get("/policy/getpolicy")
				.param("policyNumber", policyNumber)
				).andExpect(status().isInternalServerError());
		verify(policyService).getPolicyDetails(policyNumber);
		
	}
}
