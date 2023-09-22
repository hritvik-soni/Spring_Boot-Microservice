package com.learning.apigateway.config;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestAuthOutput {

    private String userEmail;
    private String userPassword;
    private String roles;


}
