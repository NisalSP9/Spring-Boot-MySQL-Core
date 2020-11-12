package com.example.demo.controller;

import com.example.demo.commons.JWTUtil;
import com.example.demo.commons.Util;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.wrapper.UserLoginRequestWrapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    final static Log logger = LogFactory.getLog(Util.class);

    @Autowired
    private UserService userService;

    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("get/token")
    public ResponseEntity userLogin(@RequestBody UserLoginRequestWrapper login){
        Optional<User> optionalUser = userService.getUserByUsername(login.getUsername());
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            if(user.getPassword().equals(login.getPassword())){
                String token = JWTUtil.createJWT("userID : "+user.getId(), "super_admin", "login", 50000L);
                return new ResponseEntity<>(token, HttpStatus.OK);
            }else {
                return new ResponseEntity<>("Invalid credentials",HttpStatus.UNAUTHORIZED);
            }
        }else {
            throw new ResourceNotFoundException("user","username",login.getUsername());
        }
    }



}
