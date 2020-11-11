package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.User;
import com.example.demo.model.UserRoles;
import com.example.demo.service.UserRolesService;
import com.example.demo.service.UserService;
import jdk.nashorn.internal.ir.RuntimeNode;
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

    @Autowired
    private UserService userService;

    @Autowired
    private UserRolesService userRolesService;

    @PostMapping("/create")
    public ResponseEntity createUser(@RequestBody User user, @RequestHeader Map<String, String> headers){
        headers.forEach((key, value) -> {
            System.out.println((String.format("Header '%s' = %s", key, value)));
        });
        User createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("/get/users")
    public ResponseEntity getUsers(){
        List<User> users = userService.getUsers();
        return new ResponseEntity<>(users,HttpStatus.OK);
    }

    @GetMapping("/get/user/{id}")
    public ResponseEntity getUserById(@PathVariable Long id){

        Optional<User> optionalUser = userService.getUserById(id);

        if(optionalUser.isPresent()){
            return new ResponseEntity<>(optionalUser.get(),HttpStatus.OK);
        }else {
            throw new ResourceNotFoundException("User","Id",id);
        }

    }

    @PostMapping("/add/role")
    public ResponseEntity addRoleToUser(@RequestBody UserRoles userRoles){
        UserRoles creUserRoles = userRolesService.createUserRoles(userRoles);
        return new ResponseEntity<>(creUserRoles,HttpStatus.CREATED);
    }


}
