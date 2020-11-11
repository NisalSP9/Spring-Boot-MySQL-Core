package com.example.demo.service;

import com.example.demo.model.RolePolicies;
import com.example.demo.model.UserRoles;
import com.example.demo.repository.RolePoliciesRepository;
import com.example.demo.repository.UserRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRolesService {

    @Autowired
    private UserRolesRepository userRolesRepository;

    public UserRoles createUserRoles(UserRoles userRoles){
        return userRolesRepository.save(userRoles);
    }



}
