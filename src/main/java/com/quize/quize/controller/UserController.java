package com.quize.quize.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quize.quize.entities.User;
import com.quize.quize.services.UserService;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    UserService userService;
    
    @PostMapping("signup")
    public ResponseEntity<String> signup(@RequestBody User user){
        return userService.signupUser(user.getUserName(),user.getPassword());
    }

    @PostMapping("login")
    public ResponseEntity<String> loginUser(@RequestBody User user){
        return userService.loginUser(user.getUserName(),user.getPassword());
    }
}
