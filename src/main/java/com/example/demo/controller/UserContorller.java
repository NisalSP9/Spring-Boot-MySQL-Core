package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserContorller {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity createUser(@RequestBody User user){
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


}
