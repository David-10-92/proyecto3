package com.auth_service_api.auth_service.controller;

import com.auth_service_api.auth_service.common.constants.ApiPathConstants;
import com.auth_service_api.auth_service.common.dtos.CreateUser;
import com.auth_service_api.auth_service.common.dtos.LoginUser;
import com.auth_service_api.auth_service.common.dtos.TokenResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(ApiPathConstants.V1_ROUTER + ApiPathConstants.AUTH_ROUTER)
public interface AuthController {

    @PostMapping(value = "/register")
    ResponseEntity<TokenResponse> createUser(@Valid @RequestBody CreateUser createUser);

    @PostMapping(value = "/login")
    ResponseEntity<TokenResponse> loginUser(@Valid @RequestBody LoginUser loginUser);
}
