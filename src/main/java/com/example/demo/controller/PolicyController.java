package com.example.demo.controller;

import com.example.demo.commons.JWTUtil;
import com.example.demo.commons.Util;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.UnauthorizedException;
import com.example.demo.model.Policy;
import com.example.demo.service.PolicyService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.jsonwebtoken.Claims;


import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/policy")
public class PolicyController {

    private final String RESOURCE = "POLICY";
    private final Integer GET = 1;
    private final Integer POST = 2;
    private final Integer DELETE = 3;
    private final Integer PUT = 4;

    private final static Log logger = LogFactory.getLog(Util.class);

    @Autowired
    private PolicyService policyService;

    @Autowired
    private Util util;

    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("/create")
    public ResponseEntity createPolicy(@RequestBody Policy policy, @RequestHeader Map<String, String> headers) {
        String token = null;
        if (headers.get("authorization") != null) {
            token = headers.get("authorization").split(" ")[1];
        } else {
            throw new ResourceNotFoundException("authorization", "token", token);
        }
        try {
            Long loginUserID = jwtUtil.decodeJWT(token);
            boolean hasAccess = util.hasAccess(loginUserID, RESOURCE, POST);
            logger.info("hasAccess : " + hasAccess);
            if (hasAccess) {
                Policy createdPolicy = policyService.create(policy);
                return new ResponseEntity<>(createdPolicy, HttpStatus.CREATED);
            } else {
                throw new UnauthorizedException(RESOURCE, "loginUserID", loginUserID);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/policies")
    public ResponseEntity getAllPolicies(@RequestHeader Map<String, String> headers) {
        String token = null;
        if (headers.get("authorization") != null) {
            token = headers.get("authorization").split(" ")[1];
        } else {
            throw new ResourceNotFoundException("authorization", "token", token);
        }
        try {
            Long loginUserID = jwtUtil.decodeJWT(token);
            boolean hasAccess = util.hasAccess(loginUserID, RESOURCE, GET);
            logger.info("hasAccess : " + hasAccess);
            if (hasAccess) {
                List<Policy> policies = policyService.getAllPolicies();
                return new ResponseEntity<>(policies, HttpStatus.OK);
            } else {
                throw new UnauthorizedException(RESOURCE, "loginUserID", loginUserID);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/policies/{userID}")
    public ResponseEntity getPoliciesByUserID(@PathVariable Long userID, @RequestHeader Map<String, String> headers) {
        String token = null;
        if (headers.get("authorization") != null) {
            token = headers.get("authorization").split(" ")[1];
        } else {
            throw new ResourceNotFoundException("authorization", "token", token);
        }
        Long loginUserID = jwtUtil.decodeJWT(token);
        try {
            boolean hasAccess = util.hasAccess(loginUserID, RESOURCE, GET);
            logger.info("hasAccess : " + hasAccess);
            if (hasAccess) {
                List<String> userPolicies = policyService.getUserPolicies(userID);
                return new ResponseEntity<>(userPolicies, HttpStatus.OK);
            } else {
                throw new UnauthorizedException(RESOURCE, "loginUserID", loginUserID);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("delete/{Id}")
    public ResponseEntity deletePolicyById(@PathVariable Long Id, @RequestHeader Map<String, String> headers) {
        String token = null;
        if (headers.get("authorization") != null) {
            token = headers.get("authorization").split(" ")[1];
        } else {
            throw new ResourceNotFoundException("authorization", "token", token);
        }
        Long loginUserID = jwtUtil.decodeJWT(token);
        try {
            boolean hasAccess = util.hasAccess(loginUserID, RESOURCE, DELETE);
            logger.info("hasAccess : " + hasAccess);
            if (hasAccess) {
                Policy policy = policyService.deletePolicyId(Id);
                return new ResponseEntity<>(policy, HttpStatus.OK);
            } else {
                throw new UnauthorizedException(RESOURCE, "loginUserID", loginUserID);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity updatePolicy(@RequestBody Policy policy, @RequestHeader Map<String, String> headers) {
        String token = null;
        if (headers.get("authorization") != null) {
            token = headers.get("authorization").split(" ")[1];
        } else {
            throw new ResourceNotFoundException("authorization", "token", token);
        }
        try {
            Long loginUserID = jwtUtil.decodeJWT(token);
            boolean hasAccess = util.hasAccess(loginUserID, RESOURCE, PUT);
            logger.info("hasAccess : " + hasAccess);
            if (hasAccess) {
                Policy updatedPolicy = policyService.create(policy);
                return new ResponseEntity<>(updatedPolicy, HttpStatus.OK);
            } else {
                throw new UnauthorizedException(RESOURCE, "loginUserID", loginUserID);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}
