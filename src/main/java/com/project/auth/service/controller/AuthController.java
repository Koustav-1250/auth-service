package com.project.auth.service.controller;

import com.project.auth.service.dao.entity.User;
import com.project.auth.service.domain.requests.AuthenticationRequest;
import com.project.auth.service.domain.response.AuthResponse;
import com.project.auth.service.domain.response.UserResponse;
import com.project.auth.service.service.AuthService;
import com.project.auth.service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {


    private final AuthService authService;


    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refreshToken(@RequestHeader("Authorization") String oldToken) {
        String token = authService.refreshToken(oldToken.substring(7));
        return ResponseEntity.ok(AuthResponse.builder().token(token).build());
    }

    @PostMapping("/revoke")
    public ResponseEntity<String> revokeToken(@RequestHeader("Authorization") String token) {
        authService.revokeToken(token.substring(7));
        return ResponseEntity.ok("Token revoked successfully");
    }
}