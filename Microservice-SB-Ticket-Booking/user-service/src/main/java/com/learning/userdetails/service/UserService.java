package com.learning.userdetails.service;

import com.learning.userdetails.model.User;
import com.learning.userdetails.model.dto.*;
import com.learning.userdetails.repository.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    IUserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String createUser(UserRequestInput userRequestInput) {
        String email = userRequestInput.getUserEmail();
        email = email.substring(email.length()-8,email.length());
        String role = "ROLE_USER";
        if(email.equals("@bus.com")){
            role = "ROLE_BUS";
        }
        User newUser = User.builder()
                .userName(userRequestInput.getUserName())
                .userAge(userRequestInput.getUserAge())
                .userEmail(userRequestInput.getUserEmail())
                .userPassword(passwordEncoder.encode(userRequestInput.getUserPassword()))
                .userCity(userRequestInput.getUserCity())
                .userMobileNumber(userRequestInput.getUserMobileNumber())
                .gender(userRequestInput.getGender())
                .roles(role)
                .build();
        User savedUser = userRepo.save(newUser);
        return "user saved successfully!!!!";
    }

    public UserRequestOutput getUserInfo(String email) {
        User currentUser = userRepo.findByUserEmail(email);
        if(currentUser==null){
            return null;
        }
        return UserRequestOutput.builder()
                .userName(currentUser.getUserName())
                .userAge(currentUser.getUserAge())
                .userEmail(currentUser.getUserEmail())
                .userCity(currentUser.getUserCity())
                .userMobileNumber(currentUser.getUserMobileNumber())
                .gender(currentUser.getGender())
                .roles(currentUser.getRoles())
                .build();
    }

    public BusOppRequestOutput getBusUserInfo(String email, String userPassword) {
        boolean isVerified = getUserIsVerified(email, userPassword);
        if (isVerified) {

        User currentUser = userRepo.findByUserEmail(email);
        if (currentUser == null) {
            return null;
        }
        return BusOppRequestOutput.builder()
                .busOppEmail(currentUser.getUserEmail())
                .busOppNumber(currentUser.getUserMobileNumber())
                .busOppName(currentUser.getUserName())
                .build();
    }
    return null;
    }

    public UserDetailsForTicket getUserInfoForTicket(String email) {
        User currentUser = userRepo.findByUserEmail(email);
        if(currentUser==null){
            return null;
        }
        return UserDetailsForTicket.builder()
                .userName(currentUser.getUserName())
                .userEmail(currentUser.getUserEmail())
                .userMobileNumber(currentUser.getUserMobileNumber())
                .userAge(currentUser.getUserAge())
                .gender(currentUser.getGender())

                .build();

    }

    public boolean getUserIsVerified(String email, String password) {
        User currUser = userRepo.findByUserEmail(email);
        return currUser.getUserPassword() == password;
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public String removeUser(String email, String password) {
        password = passwordEncoder.encode(password);
        boolean isVerified = getUserIsVerified(email,password);
        if(isVerified){
            User currUser = userRepo.findByUserEmail(email);
            userRepo.delete(currUser);
            return "User deleted Successfully!!!";
        }
        return "Invalid Credentials!!!";
    }

    public String updateUser(String email, String password, UserUpdateRequestInput updateRequestInput) {
        password = passwordEncoder.encode(password);
        boolean isVerified = getUserIsVerified(email,password);
        if(isVerified){
            User currUser = userRepo.findByUserEmail(email);
            if(updateRequestInput.getUserCity()!=null){
                currUser.setUserCity(updateRequestInput.getUserCity());
            }
            if(updateRequestInput.getUserPassword()!=null){
                currUser.setUserPassword(passwordEncoder.encode(updateRequestInput.getUserPassword()));
            }
            if(updateRequestInput.getUserMobileNumber()!=null){
                currUser.setUserMobileNumber(updateRequestInput.getUserMobileNumber());
            }
            userRepo.save(currUser);

            return "User details updated Successfully!!!";
        }
        return "Invalid Credentials!!!";

    }

    public UserRequestAuthOutput getUserInfoForAuth(String email) {
        User currUser = userRepo.findByUserEmail(email);
        if (currUser == null) {
            return null;
        }
        return  UserRequestAuthOutput.builder()
                .userEmail(currUser.getUserEmail())
                .userPassword(currUser.getUserPassword())
                .roles(currUser.getRoles())
                .build();
    }
}
