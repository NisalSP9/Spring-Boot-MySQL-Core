package com.example.demo.controller;

import com.example.demo.model.Role;
import com.example.demo.model.RolePolicies;
import com.example.demo.service.RolePoliciesService;
import com.example.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private RolePoliciesService rolePoliciesService;

    @PostMapping("/create")
    public ResponseEntity createRole(@RequestBody Role role){
        Role createdRole = roleService.create(role);
        return new ResponseEntity<>(createdRole, HttpStatus.CREATED);
    }

    @GetMapping("/get/roles")
    public ResponseEntity getAllRoles(){
        List<Role> roles = roleService.getAllRoles();
        return new ResponseEntity<>(roles,HttpStatus.OK);
    }

    @PostMapping("/add/policy")
    public ResponseEntity addPolicyToRole(@RequestBody RolePolicies rolePolicies){
        RolePolicies createdRolePolicies = rolePoliciesService.createRolePolicies(rolePolicies);
        return new ResponseEntity<>(createdRolePolicies,HttpStatus.CREATED);
    }




}
