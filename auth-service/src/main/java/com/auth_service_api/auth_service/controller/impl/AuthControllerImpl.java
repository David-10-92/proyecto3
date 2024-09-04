package com.auth_service_api.auth_service.controller.impl;

import com.auth_service_api.auth_service.common.dtos.CreateUser;
import com.auth_service_api.auth_service.common.dtos.LoginUser;
import com.auth_service_api.auth_service.common.dtos.TokenResponse;
import com.auth_service_api.auth_service.controller.AuthController;
import com.auth_service_api.auth_service.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthControllerImpl implements AuthController {

    private final AuthService authService;

    public AuthControllerImpl(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public ResponseEntity<TokenResponse> createUser(CreateUser createUser) {
        return ResponseEntity.ok(authService.createrUser(createUser));
    }

    @Override
    public ResponseEntity<TokenResponse> loginUser(LoginUser loginUser) {
        return ResponseEntity.ok(authService.loginUser(loginUser));
    }
}
