package com.project.auth.service.controller;


import com.project.auth.service.dao.entity.User;
import com.project.auth.service.domain.requests.AuthenticationRequest;
import com.project.auth.service.domain.response.UserResponse;
import com.project.auth.service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<UserResponse> signUp(@RequestBody AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(userService.signUp(authenticationRequest));
    }

    @PostMapping("/sign-in")
    public ResponseEntity<UserResponse> signIn(@RequestBody AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(userService.signIn(authenticationRequest.getEmailId(), authenticationRequest.getPassword()));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable String id) {
        return ResponseEntity.ok(userService.getUser(id));
    }
}
