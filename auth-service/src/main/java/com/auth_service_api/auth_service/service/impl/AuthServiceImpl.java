package com.auth_service_api.auth_service.service.impl;

import com.auth_service_api.auth_service.common.dtos.CreateUser;
import com.auth_service_api.auth_service.common.dtos.LoginUser;
import com.auth_service_api.auth_service.common.dtos.TokenResponse;
import com.auth_service_api.auth_service.repository.UserRepository;
import com.auth_service_api.auth_service.service.AuthService;
import com.auth_service_api.auth_service.service.JwtService;
import com.common_library.library.entities.UserModel;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(UserRepository userRespository, JwtService jwtService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRespository;
        this.jwtService = jwtService;
        this.passwordEncoder = (BCryptPasswordEncoder) passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public TokenResponse createrUser(CreateUser createUser) {
        return Optional.of(createUser)
                .map(this::mapToEntity)
                .map(userRepository::save)
                .map(existUser -> jwtService.generateToken(existUser.getUserId()))
                .orElseThrow(() -> new RuntimeException("Error creating user"));
    }

    @Override
    public TokenResponse loginUser(LoginUser loginUser) {
        return Optional.of(loginUser)
                .map(req -> userRepository.findByEmail(req.getEmail()))
                .filter(user -> passwordEncoder.matches(loginUser.getPassword(), user.get().getPassword()))
                .map(user -> user.get().getUserId())
                .map(jwtService::generateToken)
                .orElseThrow(() -> new RuntimeException("Invalid login"));
    }

    private UserModel mapToEntity(CreateUser createUser) {
        return UserModel.builder()
                .username(createUser.getName())
                .email(createUser.getEmail())
                .password(passwordEncoder.encode(createUser.getPassword()))
                .role("USER")
                .build();
    }

    private String authenticate(String email, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            return email;
        } catch (AuthenticationException e) {
            throw new RuntimeException("Authentication failed", e);
        }
    }
}
