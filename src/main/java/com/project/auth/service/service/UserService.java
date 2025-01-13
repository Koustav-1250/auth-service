package com.project.auth.service.service;


import com.project.auth.service.domain.requests.AuthenticationRequest;
import com.project.auth.service.domain.response.UserResponse;

public interface UserService {

    UserResponse signUp(AuthenticationRequest authenticationRequest);
    UserResponse signIn(String email, String password);

    UserResponse getUser(String id);



}
