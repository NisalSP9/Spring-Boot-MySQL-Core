package com.example.demo.controller;

import com.example.demo.commons.JWTUtil;
import com.example.demo.commons.Util;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.UnauthorizedException;
import com.example.demo.model.User;
import com.example.demo.model.UserRoles;
import com.example.demo.service.UserRolesService;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jdk.nashorn.internal.ir.RuntimeNode;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserContorller {

    private final String RESOURCE = "USER";
    private final Integer GET = 1;
    private final Integer POST = 2;
    private final Integer DELETE = 3;
    private final Integer PUT = 4;

    private final static Log logger = LogFactory.getLog(Util.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserRolesService userRolesService;

    @Autowired
    private Util util;

    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("/create")
    public ResponseEntity createUser(@RequestBody User user, @RequestHeader Map<String, String> headers) {
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
                User createdUser = userService.createUser(user);
                return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
            } else {
                throw new UnauthorizedException(RESOURCE, "loginUserID", loginUserID);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/users")
    public ResponseEntity getUsers(@RequestHeader Map<String, String> headers) {
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
                List<User> users = userService.getUsers();
                return new ResponseEntity<>(users, HttpStatus.OK);
            } else {
                throw new UnauthorizedException(RESOURCE, "loginUserID", loginUserID);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/user/{id}")
    public ResponseEntity getUserById(@PathVariable Long id, @RequestHeader Map<String, String> headers) {

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

                Optional<User> optionalUser = userService.getUserById(id);

                if (optionalUser.isPresent()) {
                    return new ResponseEntity<>(optionalUser.get(), HttpStatus.OK);
                } else {
                    throw new ResourceNotFoundException("User", "Id", id);
                }
            } else {
                throw new UnauthorizedException(RESOURCE, "loginUserID", loginUserID);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/add/role")
    public ResponseEntity addRoleToUser(@RequestBody UserRoles userRoles, @RequestHeader Map<String, String> headers) {
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
                UserRoles creUserRoles = userRolesService.createUserRoles(userRoles);
                return new ResponseEntity<>(creUserRoles, HttpStatus.CREATED);
            } else {
                throw new UnauthorizedException(RESOURCE, "loginUserID", loginUserID);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}
