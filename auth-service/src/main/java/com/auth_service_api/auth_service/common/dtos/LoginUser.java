package com.auth_service_api.auth_service.common.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class LoginUser {
    @NonNull
    private String email;
    @NotNull
    private String password;
}
