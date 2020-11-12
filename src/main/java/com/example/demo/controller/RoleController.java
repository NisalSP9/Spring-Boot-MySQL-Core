package com.example.demo.controller;

import com.example.demo.commons.JWTUtil;
import com.example.demo.commons.Util;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.UnauthorizedException;
import com.example.demo.model.Role;
import com.example.demo.model.RolePolicies;
import com.example.demo.service.RolePoliciesService;
import com.example.demo.service.RoleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/role")
public class RoleController {

    private final String RESOURCE = "ROLE";
    private final Integer GET = 1;
    private final Integer POST = 2;
    private final Integer DELETE = 3;
    private final Integer PUT = 4;

    private final static Log logger = LogFactory.getLog(Util.class);

    @Autowired
    private RoleService roleService;

    @Autowired
    private RolePoliciesService rolePoliciesService;

    @Autowired
    private Util util;

    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("/create")
    public ResponseEntity createRole(@RequestBody Role role, @RequestHeader Map<String, String> headers) {
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
                Role createdRole = roleService.create(role);
                return new ResponseEntity<>(createdRole, HttpStatus.CREATED);
            } else {
                throw new UnauthorizedException(RESOURCE, "loginUserID", loginUserID);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/roles")
    public ResponseEntity getAllRoles(@RequestHeader Map<String, String> headers) {
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
                List<Role> roles = roleService.getAllRoles();
                return new ResponseEntity<>(roles, HttpStatus.OK);
            } else {
                throw new UnauthorizedException(RESOURCE, "loginUserID", loginUserID);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add/policy")
    public ResponseEntity addPolicyToRole(@RequestBody RolePolicies rolePolicies, @RequestHeader Map<String, String> headers) {
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
                RolePolicies createdRolePolicies = rolePoliciesService.createRolePolicies(rolePolicies);
                return new ResponseEntity<>(createdRolePolicies, HttpStatus.CREATED);
            } else {
                throw new UnauthorizedException(RESOURCE, "loginUserID", loginUserID);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{Id}")
    public ResponseEntity deleteRoleById(@PathVariable Long Id, @RequestHeader Map<String, String> headers){
        String token = null;
        if (headers.get("authorization") != null) {
            token = headers.get("authorization").split(" ")[1];
        } else {
            throw new ResourceNotFoundException("authorization", "token", token);
        }
        try {
            Long loginUserID = jwtUtil.decodeJWT(token);
            boolean hasAccess = util.hasAccess(loginUserID, RESOURCE, DELETE);
            logger.info("hasAccess : " + hasAccess);
            if (hasAccess) {
        Role role = roleService.deleteRoleId(Id);
        return new ResponseEntity<>(role,HttpStatus.OK);
            } else {
                throw new UnauthorizedException(RESOURCE, "loginUserID", loginUserID);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity updateRole(@RequestBody Role role, @RequestHeader Map<String, String> headers) {
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
                Role createdRole = roleService.create(role);
                return new ResponseEntity<>(createdRole, HttpStatus.OK);
            } else {
                throw new UnauthorizedException(RESOURCE, "loginUserID", loginUserID);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}
