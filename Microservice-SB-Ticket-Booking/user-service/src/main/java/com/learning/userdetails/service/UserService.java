package com.learning.userdetails.service;

import com.learning.userdetails.model.Users;
import com.learning.userdetails.model.dto.*;
import com.learning.userdetails.repository.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
        Users newUsers = Users.builder()
                .userName(userRequestInput.getUserName())
                .userAge(userRequestInput.getUserAge())
                .userEmail(userRequestInput.getUserEmail())
                .userPassword(passwordEncoder.encode(userRequestInput.getUserPassword()))
                .userCity(userRequestInput.getUserCity())
                .userMobileNumber(userRequestInput.getUserMobileNumber())
                .gender(userRequestInput.getGender())
                .roles(role)
                .build();
        Users savedUsers = userRepo.save(newUsers);
        return "user saved successfully!!!!";
    }

    public UserRequestOutput getUserInfo(String email) {
        Optional<Users> currentUsers = userRepo.findByUserEmail(email);
        return currentUsers.map(users -> UserRequestOutput.builder()
                .userName(users.getUserName())
                .userAge(users.getUserAge())
                .userEmail(users.getUserEmail())
                .userCity(users.getUserCity())
                .userMobileNumber(users.getUserMobileNumber())
                .gender(users.getGender())
                .roles(users.getRoles())
                .build()).orElse(null);
    }

    public BusOppRequestOutput getBusUserInfo(String email) {
//        boolean isVerified = getUserIsVerified(email, userPassword);
//        if (isVerified) { }

        Optional<Users> currentUsers = userRepo.findByUserEmail(email);
        return currentUsers.map(users -> BusOppRequestOutput.builder()
                .busOppEmail(users.getUserEmail())
                .busOppNumber(users.getUserMobileNumber())
                .busOppName(users.getUserName())
                .build()).orElse(null);
    }

    public UserDetailsForTicket getUserInfoForTicket(String email) {
        Optional<Users> currentUsers = userRepo.findByUserEmail(email);

        return currentUsers.map(users -> UserDetailsForTicket.builder()
                .userName(users.getUserName())
                .userEmail(users.getUserEmail())
                .userMobileNumber(users.getUserMobileNumber())
                .userAge(users.getUserAge())
                .gender(users.getGender())

                .build()).orElse(null);

    }

    public boolean getUserIsVerified(String email, String password) {
        Optional<Users> currUsers = userRepo.findByUserEmail(email);
        return Objects.equals(currUsers.get().getUserPassword(), password);
    }

    public List<Users> getAllUsers() {
        return userRepo.findAll();
    }

    public String removeUser(String email) {
//        password = passwordEncoder.encode(password);
//        boolean isVerified = getUserIsVerified(email,password);
//        if(isVerified){
        if(userRepo.findByUserEmail(email).isPresent()){
            Optional<Users> currUsers = userRepo.findByUserEmail(email);
            Integer userId= currUsers.get().getUserId();
//            userRepo.delete(currUsers);
            userRepo.deleteById(userId);
            return "User deleted Successfully!!!";
        }
        return "Invalid Credentials!!!";
    }

//    public String updateUser(String email, String password, UserUpdateRequestInput updateRequestInput) {
//        password = passwordEncoder.encode(password);
//        boolean isVerified = getUserIsVerified(email,password);
//
//        if(isVerified){
//
//           Optional<Users> currUsers = userRepo.findByUserEmail(email);
//
//           Integer userId = currUsers.get().getUserId();
//
//            if(updateRequestInput.getUserCity()!=null){
//                currUsers.get().setUserCity(updateRequestInput.getUserCity());
//            }
//            if(updateRequestInput.getUserPassword()!=null){
//                currUsers.get().setUserPassword(passwordEncoder.encode(updateRequestInput.getUserPassword()));
//            }
//            if(updateRequestInput.getUserMobileNumber()!=null){
//                currUsers.get().setUserMobileNumber(updateRequestInput.getUserMobileNumber());
//            }
//
//             userRepo.save(currUsers);
//
//            return "User details updated Successfully!!!";
//        }
//        return "Invalid Credentials!!!";
//
//    }

    public UserRequestAuthOutput getUserInfoForAuth(String email) {
        Optional<Users> currUsers = userRepo.findByUserEmail(email);
        return currUsers.map(users -> UserRequestAuthOutput.builder()
                .userEmail(users.getUserEmail())
                .userPassword(users.getUserPassword())
                .roles(users.getRoles())
                .build()).orElse(null);
    }
}
