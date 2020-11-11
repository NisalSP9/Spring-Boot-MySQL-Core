package com.example.demo.service;

import com.example.demo.model.RolePolicies;
import com.example.demo.repository.RolePoliciesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolePoliciesService {

    @Autowired
    private RolePoliciesRepository rolePoliciesRepository;

    public RolePolicies createRolePolicies(RolePolicies rolePolicies){
        return rolePoliciesRepository.save(rolePolicies);
    }



}
