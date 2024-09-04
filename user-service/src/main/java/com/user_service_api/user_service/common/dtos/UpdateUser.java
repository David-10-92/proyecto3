package com.user_service_api.user_service.common.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UpdateUser {
    @NotNull
    private String username;
    @NotNull
    private String email;
}
