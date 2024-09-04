package com.user_service_api.user_service.service.impl;

import com.common_library.library.entities.UserModel;
import com.user_service_api.user_service.common.dtos.UpdateUser;
import com.user_service_api.user_service.repository.UserRepository;
import com.user_service_api.user_service.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserModel getUser(String id,Long userId) {
        return Optional.of(userId)
                .flatMap(userRepository::findById)
                .orElseThrow(() -> new RuntimeException("User nor found"));
    }

    @Override
    public void updateUser(String id,UpdateUser updateUser, Long userId) {
        Optional.of(userId)
                .map(this::getUserByUserId)
                .map(existUser -> updateFieldsUser(existUser,updateUser))
                .map(userRepository::save);
    }

    private UserModel getUserByUserId(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User nor found"));
    }

    private UserModel updateFieldsUser(UserModel existUser,UpdateUser updateUser){
        existUser.setEmail(updateUser.getEmail());
        existUser.setUsername(updateUser.getUsername());
        return existUser;
    }

    @Override
    public void deleteUser(String id,Long userId) {
        Optional.of(userId)
                .map(this::getUserByUserId)
                .ifPresent(userRepository::delete);
    }
}
