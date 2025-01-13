package com.project.auth.service.service.impl;

import com.project.auth.service.dao.entity.User;
import com.project.auth.service.dao.repository.UserRepository;
import com.project.auth.service.domain.requests.AuthenticationRequest;
import com.project.auth.service.domain.response.UserResponse;
import com.project.auth.service.security.JwtUtil;
import com.project.auth.service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;


    @SneakyThrows
    @Override
    public UserResponse signUp(AuthenticationRequest authenticationRequest) {
        Optional<User> optionalUser = userRepository.findByEmail(authenticationRequest.getEmailId());
        if (optionalUser.isPresent()) throw new RuntimeException("User with this emailId already exists");
        User user = new User();
        user.setEmail(authenticationRequest.getEmailId());
        user.setPassword(passwordEncoder.encode(authenticationRequest.getPassword()));
        user = userRepository.save(user);
        return UserResponse.builder()
                .userId(user.getId())
                .email(user.getEmail()).build();
    }

    @Override
    public UserResponse signIn(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        String token = jwtUtil.generateToken(user.getEmail());
        return UserResponse.builder()
                .token(token)
                .userId(user.getId())
                .email(user.getEmail()).build();
    }

    @Override
    public UserResponse getUser(String id) {
        return userRepository.findById(id)
                .map(user -> UserResponse.builder()
                        .userId(user.getId())
                        .email(user.getEmail()).build())
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
