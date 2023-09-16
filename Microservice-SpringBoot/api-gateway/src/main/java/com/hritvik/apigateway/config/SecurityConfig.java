package com.hritvik.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(ServerHttpSecurity http) throws Exception {
        return http.csrf(t -> {
                    AbstractHttpConfigurer.disable(t);
                })
                .authorizeHttpRequests(auth-> auth.requestMatchers("/api/**","/eureka/**").permitAll()
                        .requestMatchers("/eureka/web").authenticated())
                .httpBasic(Customizer.withDefaults()).build();

    }

}
