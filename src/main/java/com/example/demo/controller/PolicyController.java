package com.example.demo.controller;

import com.example.demo.model.Policy;
import com.example.demo.service.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/policy")
public class PolicyController {

    @Autowired
    private PolicyService policyService;

    @PostMapping("/create")
    public ResponseEntity createPolicy(@RequestBody Policy policy){
        Policy createdPolicy = policyService.create(policy);
        return new ResponseEntity<>(createdPolicy, HttpStatus.CREATED);
    }

    @GetMapping("/get/policies")
    public ResponseEntity getAllPolicies(){
        List<Policy> policies = policyService.getAllPolicies();
        return new ResponseEntity<>(policies,HttpStatus.OK);
    }




}
