package com.policy.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.policy.dto.PdfResponseDto;
import com.policy.dto.PolicyRequestDto;
import com.policy.dto.PolicyResponseDto;
import com.policy.service.PolicyService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/policy")
public class PolicyController {
	
	@Autowired
	private PolicyService policyService;
	
	@GetMapping("/welcome")
	public String showWelcome() {
		return "Hey user welcome to poicy generator";
	}
	
	@PostMapping("/savepolicy")
	public PolicyResponseDto savePolicy(@Valid @RequestBody PolicyRequestDto policyRequestDto ) {
		return policyService.savePolicyIntoHub(policyRequestDto);	
	}
	
	@GetMapping("/getpolicy")
	public PdfResponseDto getPolicyDetails( @RequestParam String policyNumber ) throws IOException {
		 return policyService.getPolicyDetails(policyNumber);
	}
}
