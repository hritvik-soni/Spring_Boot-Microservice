package com.learning.userdetails.config;


import com.learning.userdetails.model.Users;
import com.learning.userdetails.repository.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private IUserRepo repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<Users> credential = repository.findByUserEmail(email);
        return credential.map(CustomUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("user not found with name :" + email));
    }
}