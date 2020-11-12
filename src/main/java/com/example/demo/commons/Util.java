package com.example.demo.commons;

import com.example.demo.service.PolicyService;
import com.example.demo.wrapper.PolicyWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class Util {

    @Autowired
    private PolicyService policyService;

    final static Log logger = LogFactory.getLog(Util.class);

    public boolean hasAccess(Long userID,String resource, Integer method) throws JsonProcessingException {

        PolicyWrapper policyWrapper = new PolicyWrapper(resource.toLowerCase(),method);

        logger.info(policyWrapper.getResource());
        logger.info(policyWrapper.getAccess());

        List<String> userPolicies = policyService.getUserPolicies(userID);
        for(String policy : userPolicies){
            PolicyWrapper[] policyWrappers = new ObjectMapper().readValue(policy, PolicyWrapper[].class);
            for(PolicyWrapper wrapper:policyWrappers){
                logger.info("wrapper.getResource() : " + wrapper.getResource());
                logger.info("wrapper.getAccess() : " + wrapper.getAccess());
                logger.info(wrapper.getResource().equals(resource.toLowerCase()));
                logger.info(wrapper.getAccess()>=method);
                if(wrapper.getResource().equals(resource.toLowerCase()) && wrapper.getAccess()>=method){
                    return true;
                }
            }
        }
        return false;
    }

}
