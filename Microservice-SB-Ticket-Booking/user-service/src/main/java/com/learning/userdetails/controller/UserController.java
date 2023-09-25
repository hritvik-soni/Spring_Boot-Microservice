package com.learning.userdetails.controller;

import com.learning.userdetails.model.Users;
import com.learning.userdetails.model.dto.*;
import com.learning.userdetails.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final WebClient.Builder webClientBuilder;

    @PostMapping("/new")
    public String createUser(@RequestBody UserRequestInput userRequestInput){

        return userService.createUser(userRequestInput);
    }
    @GetMapping("/all")
    public List<Users> getAllUser(){
        return userService.getAllUsers();
    }
    @GetMapping("/info")
    public UserRequestOutput getUserInfo(@RequestParam("email") String email){
        return userService.getUserInfo(email);
    }

    @DeleteMapping("/remove")
    public String removeUser(@RequestHeader("email") String email ){
        System.out.println("Inside remove mapping for user"+email);
//            ,@RequestHeader("password") String password){

        return userService.removeUser(email);
    }

    @PutMapping("/update")
    public String updateUser(@RequestParam("email") String email, @RequestParam("password") String password,
                             @RequestBody UserUpdateRequestInput updateRequestInput){

        return userService.updateUser(email,password,updateRequestInput);
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
     *
     * Mapping for Ticket Related to User
     * @param email
     * @return
     *
     */
    @GetMapping("/info/ticket")
    public UserDetailsForTicket getUserInfoForTicket(@RequestParam("email") String email){
        return userService.getUserInfoForTicket(email);
    }

    /**
     * Mapping for Auth Related to User
     *
     *
     */
    @GetMapping("/info/auth")
    public UserRequestAuthOutput getUserInfoForAuth (@RequestParam("email") String email){
        return userService.getUserInfoForAuth(email);
    }

    /**
     * mapping for testing
     *
     */
      @GetMapping("/print")
      public String print (@RequestHeader("token") String token){
          System.out.println("inside print");
          String result = webClientBuilder.build().get()
                  .uri("http://user-service/api/user/demo?token="+token)
                  .headers(headers -> headers.setBearerAuth(token))
//                  .header("Authorization"token)
                  .retrieve()
                  .bodyToMono(String.class)
                  .block();
          return  result +"\n"+token;
      }
    @GetMapping("/demo")
    public String demo (@RequestParam("token") String token){
        System.out.println("inside demo");
        try{
            webClientBuilder.build().get()
                    .uri("http://identity-service/auth/validate?token="+token)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            return "inside valid block and verified";
        }
        catch(Exception e){
            return "verification failed";
        }


    }




}
