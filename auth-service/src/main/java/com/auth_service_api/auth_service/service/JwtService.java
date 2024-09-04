package com.auth_service_api.auth_service.service;

import com.auth_service_api.auth_service.common.dtos.TokenResponse;
import io.jsonwebtoken.Claims;

public interface JwtService {

    TokenResponse generateToken(Long userId);

    Claims getClaims(String token);

    boolean isExpired(String token);

    Integer extractedUserId(String token);
}
