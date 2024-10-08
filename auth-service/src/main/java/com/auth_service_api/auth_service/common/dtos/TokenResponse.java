package com.auth_service_api.auth_service.common.dtos;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class TokenResponse {
    private String accessToken;
}
