package com.project.auth.service.domain.requests;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationRequest {
     @Email(message = "Email must be valid")
    private String emailId;

    @NotBlank(message = "Password must not be empty")
    private String password;
}