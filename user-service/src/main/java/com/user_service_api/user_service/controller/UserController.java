package com.user_service_api.user_service.controller;

import com.common_library.library.entities.UserModel;
import com.user_service_api.user_service.common.constants.ApiPathConstants;
import com.user_service_api.user_service.common.dtos.UpdateUser;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping(ApiPathConstants.V1_ROUTER + ApiPathConstants.USER_ROUTER)
@SecurityRequirement(name = "Bearer Authentication")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public interface UserController {

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "{userId}")
    ResponseEntity<UserModel> getUser(
            @RequestHeader("userIdRequest") String id,
            @PathVariable Long userId);

    @PreAuthorize("isAuthenticated()")
    @PutMapping(value = "{userId}")
    ResponseEntity<Void> updateUser(
            @RequestHeader("userIdRequest") String id,
            @Valid @RequestBody UpdateUser updateUser, Long userId);

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping(value = "{userId}")
    ResponseEntity<Void> deleteUser(
            @RequestHeader("userIdRequest") String id,
            @PathVariable Long userId);
}
