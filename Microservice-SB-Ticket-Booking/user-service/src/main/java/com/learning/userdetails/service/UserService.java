package com.learning.userdetails.service;

import com.learning.userdetails.model.Users;
import com.learning.userdetails.model.dto.*;
import com.learning.userdetails.repository.IUserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    IUserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final WebClient.Builder webClientBuilder;

    public String createUser(UserRequestInput userRequestInput) {

        String password = passwordEncoder.encode(userRequestInput.getUserPassword());
        UserCredentialInput userCredentialInput = UserCredentialInput.builder()
                .name(userRequestInput.getUserName())
                .email(userRequestInput.getUserEmail())
                .password(password)
                .build();

        Mono<String> result =  webClientBuilder.build().post()
          .uri("http://identity-service/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(userCredentialInput)
                .retrieve()
                .bodyToMono(String.class);
                result.block();

        System.out.println("after register link call "+result.block());

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
                .userPassword(password)
                .userCity(userRequestInput.getUserCity())
                .userMobileNumber(userRequestInput.getUserMobileNumber())
                .gender(userRequestInput.getGender())
                .roles(role)
                .build();
        Users savedUsers = userRepo.save(newUsers);
        return "user saved successfully!!!!";
    }

    public UserRequestOutput getUserInfo(String email) {
        Users users = userRepo.findByUserEmail(email);

        if(users==null){
            return null;
        }
        return  UserRequestOutput.builder()
                .userName(users.getUserName())
                .userAge(users.getUserAge())
                .userEmail(users.getUserEmail())
                .userCity(users.getUserCity())
                .userMobileNumber(users.getUserMobileNumber())
                .gender(users.getGender())
                .roles(users.getRoles())
                .build();
    }

    public BusOppRequestOutput getBusUserInfo(String email) {
//        boolean isVerified = getUserIsVerified(email, userPassword);
//        if (isVerified) { }

        Users users = userRepo.findByUserEmail(email);
        if(users==null){
            return null;
        }
        return  BusOppRequestOutput.builder()
                .busOppEmail(users.getUserEmail())
                .busOppNumber(users.getUserMobileNumber())
                .busOppName(users.getUserName())
                .build();
    }

    public UserDetailsForTicket getUserInfoForTicket(String email) {
      Users users = userRepo.findByUserEmail(email);
        if(users==null){
           return null;
        }

        return  UserDetailsForTicket.builder()
                .userName(users.getUserName())
                .userEmail(users.getUserEmail())
                .userMobileNumber(users.getUserMobileNumber())
                .userAge(users.getUserAge())
                .gender(users.getGender())

                .build();

    }

    public boolean getUserIsVerified(String email, String password) {
         Users currUsers = userRepo.findByUserEmail(email);
        return Objects.equals(currUsers.getUserPassword(), password);
    }

    public List<Users> getAllUsers() {
        return userRepo.findAll();
    }

    public String removeUser(String email) {
//        password = passwordEncoder.encode(password);
//        boolean isVerified = getUserIsVerified(email,password);
//        if(isVerified){

         Users currUsers = userRepo.findByUserEmail(email);
           if(currUsers!=null){
           String result =  webClientBuilder.build().delete()
                       .uri("http://identity-service/auth/removeUser/"+email)
                       .retrieve()
                       .bodyToMono(String.class)
                       .block();

               System.out.println("after register link call "+result);
               userRepo.delete(currUsers);
            return "User deleted Successfully!!!";
           }
           return "Invalid Credentials!!!";
    }

    public String updateUser(String email, String password, UserUpdateRequestInput updateRequestInput) {
        password = passwordEncoder.encode(password);
        boolean isVerified = getUserIsVerified(email,password);

        if(isVerified){

          Users currUsers = userRepo.findByUserEmail(email);

           Integer userId = currUsers.getUserId();

            if(updateRequestInput.getUserCity()!=null){
                currUsers.setUserCity(updateRequestInput.getUserCity());
            }
            if(updateRequestInput.getUserPassword()!=null){
                currUsers.setUserPassword(passwordEncoder.encode(updateRequestInput.getUserPassword()));
            }
            if(updateRequestInput.getUserMobileNumber()!=null){
                currUsers.setUserMobileNumber(updateRequestInput.getUserMobileNumber());
            }

             userRepo.save(currUsers);

            return "User details updated Successfully!!!";
        }
        return "Invalid Credentials!!!";

    }

    public UserRequestAuthOutput getUserInfoForAuth(String email) {
        Users users = userRepo.findByUserEmail(email);

        if(users==null){
            return null;
        }
        return  UserRequestAuthOutput.builder()
                .userEmail(users.getUserEmail())
                .userPassword(users.getUserPassword())
                .roles(users.getRoles())
                .build();
    }
}
