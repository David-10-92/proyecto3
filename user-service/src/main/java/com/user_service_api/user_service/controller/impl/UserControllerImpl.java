package com.user_service_api.user_service.controller.impl;

import com.common_library.library.entities.UserModel;
import com.user_service_api.user_service.common.dtos.UpdateUser;
import com.user_service_api.user_service.controller.UserController;
import com.user_service_api.user_service.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserControllerImpl implements UserController {
    private final UserService userService;

    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<UserModel> getUser(String id,Long userId) {
        return ResponseEntity.ok(userService.getUser(id,userId));
    }

    @Override
    public ResponseEntity<Void> updateUser(String id,UpdateUser updateUser, Long userId) {
        userService.updateUser(id,updateUser,userId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> deleteUser(String id,Long userId) {
        userService.deleteUser(id,userId);
        return ResponseEntity.noContent().build();
    }
}