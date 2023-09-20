package com.learning.userdetails.controller;

import com.learning.userdetails.model.dto.UserRequestInput;
import com.learning.userdetails.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/name")
    public String createUser(@RequestBody UserRequestInput userRequestInput){
        return userService.createUser(userRequestInput);
    }

}
