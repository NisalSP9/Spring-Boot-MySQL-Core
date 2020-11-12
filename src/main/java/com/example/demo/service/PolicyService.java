package com.example.demo.service;

import com.example.demo.model.Policy;
import com.example.demo.repository.PolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PolicyService {

    @Autowired
    private PolicyRepository policyRepository;

    public Policy create(Policy policy){
        return policyRepository.save(policy);
    }

    public List<Policy> getAllPolicies(){
        return policyRepository.findAll();
    }

    public List<String> getUserPolicies(Long userID){
        return policyRepository.getUserPolicies(userID);
    }

}
