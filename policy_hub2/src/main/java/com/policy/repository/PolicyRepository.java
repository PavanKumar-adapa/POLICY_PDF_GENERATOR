package com.policy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.policy.entity.Policy;

public interface PolicyRepository extends JpaRepository<Policy, Integer>{
    List<Policy> findByPolicyNumber(String policyNumber);
}
