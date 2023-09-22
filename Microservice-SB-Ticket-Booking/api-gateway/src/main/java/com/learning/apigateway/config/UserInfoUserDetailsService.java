package com.learning.apigateway.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;



@Component
public class UserInfoUserDetailsService implements UserDetailsService {

    private  WebClient.Builder webClientBuilder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

      UserRequestAuthOutput userRequestAuthOutput = webClientBuilder.build().get()
              .uri("http://user-service/api/user/info/auth?email=" + email)
              .retrieve()
              .bodyToMono(UserRequestAuthOutput.class)
              .block();

       if(userRequestAuthOutput==null){
           throw new UsernameNotFoundException("user not found " + email);
       }

        return new UserInfoUserDetails(userRequestAuthOutput);

    }

}
