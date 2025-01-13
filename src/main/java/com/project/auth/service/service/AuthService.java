package com.project.auth.service.service;

public interface AuthService {
    String refreshToken(String token);
    void revokeToken(String token);
}
