package com.learning.userdetails.service;

import com.learning.userdetails.model.User;
import com.learning.userdetails.model.dto.UserRequestInput;
import com.learning.userdetails.repository.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    IUserRepo userRepo;

    public String createUser(UserRequestInput userRequestInput) {
        String email = userRequestInput.getUserEmail();
        email = email.substring(email.length()-8,email.length());
        String role = "ROLE_USER";
        if(email.equals("@bus.com")){
            role = "ROLE_BUS";
        }
        User user = User.builder().build();
    }
}
