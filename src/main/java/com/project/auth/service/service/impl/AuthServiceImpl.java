package com.project.auth.service.service.impl;


import com.project.auth.service.dao.entity.User;
import com.project.auth.service.dao.repository.UserRepository;
import com.project.auth.service.security.JwtUtil;
import com.project.auth.service.service.AuthService;
import com.project.auth.service.service.TokenBlacklistService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JwtUtil tokenProvider;
    private final TokenBlacklistService tokenBlacklistService;


    @Override
    public String refreshToken(String token) {
        if (tokenProvider.validateToken(token) && !tokenBlacklistService.isTokenBlacklisted(token)) {
            return tokenProvider.generateToken(tokenProvider.getUserEmailFromJWT(token));
        }
        return null;
    }

    @Override
    public void revokeToken(String token) {
        tokenBlacklistService.blacklistToken(token);
    }
}