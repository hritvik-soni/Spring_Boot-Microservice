package com.learning.userdetails.controller;

import com.learning.userdetails.model.User;
import com.learning.userdetails.model.dto.BusOppRequestOutput;
import com.learning.userdetails.model.dto.UserDetailsForTicket;
import com.learning.userdetails.model.dto.UserRequestInput;
import com.learning.userdetails.model.dto.UserRequestOutput;
import com.learning.userdetails.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/new")
    public String createUser(@RequestBody UserRequestInput userRequestInput){
        return userService.createUser(userRequestInput);
    }
    @GetMapping("/all")
    public List<User> getAllUser(){
        return userService.getAllUsers();
    }
    @GetMapping("/info")
    public UserRequestOutput getUserInfo(@RequestParam("email") String email){
        return userService.getUserInfo(email);
    }

    /**
     * Mapping for Bus Related to User
     * @param email
     * @return
     */

    @GetMapping("/info/bus")
    public BusOppRequestOutput getBusUserInfo(@RequestParam("email") String email){
        return userService.getBusUserInfo(email);
    }
    @GetMapping("/info/bus/isVerified")
    public boolean getUserIsVerified(@RequestParam("email") String email ,@RequestParam("password")String password){
        return userService.getUserIsVerified(email,password);
    }

    /**
     * Mapping for Ticket Related to User
     * @param email
     * @return
     */
    @GetMapping("/info/ticket")
    public UserDetailsForTicket getUserInfoForTicket(@RequestParam("email") String email){
        return userService.getUserInfoForTicket(email);
    }
//    @DeleteMapping("/remove")
//    public String removeUser(@RequestParam("email") String email,@RequestParam("password") String password)


}
