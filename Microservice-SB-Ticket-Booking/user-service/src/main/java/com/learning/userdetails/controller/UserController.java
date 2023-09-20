package com.learning.userdetails.controller;

import com.learning.userdetails.model.dto.BusOppRequestInput;
import com.learning.userdetails.model.dto.UserRequestInput;
import com.learning.userdetails.model.dto.UserRequestOutput;
import com.learning.userdetails.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/name")
    public String createUser(@RequestBody UserRequestInput userRequestInput){
        return userService.createUser(userRequestInput);
    }
    @GetMapping("/info")
    public UserRequestOutput getUserInfo(@RequestParam String email){
        return userService.getUserInfo(email);
    }
    @GetMapping("/info/bus")
    public BusOppRequestInput getBusUserInfo(@RequestParam String email){
        return userService.getBusUserInfo(email);
    }

}