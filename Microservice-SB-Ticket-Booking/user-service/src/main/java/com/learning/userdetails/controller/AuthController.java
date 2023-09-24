package com.learning.userdetails.controller;

import com.learning.userdetails.model.dto.AuthRequest;
import com.learning.userdetails.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService service;

    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("/token")
    @PreAuthorize("hasAuthority('ROLE_USER,ROLE_BUS')")
    public String getToken(@RequestBody AuthRequest authRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        if (authenticate.isAuthenticated()) {
            return service.generateToken(authRequest.getEmail());
        } else {
            throw new RuntimeException("invalid access");
        }
    }

    @GetMapping("/validate")
    public void validateToken(@RequestParam("token") String token) {
       service.validateToken(token);


    }
    @GetMapping("/print")
    public String printToken(@RequestHeader("email") String email) {
        System.out.println("inside print get mapping working "+email);
        return "inside print get mapping working "+email;
    }
}