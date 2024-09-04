package com.auth_service_api.auth_service.service;

import com.auth_service_api.auth_service.common.dtos.CreateUser;
import com.auth_service_api.auth_service.common.dtos.LoginUser;
import com.auth_service_api.auth_service.common.dtos.TokenResponse;

public interface AuthService {
    TokenResponse createrUser(CreateUser createUser);

    TokenResponse loginUser(LoginUser loginUser);
}
