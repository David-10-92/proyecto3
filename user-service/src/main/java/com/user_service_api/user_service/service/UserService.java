package com.user_service_api.user_service.service;

import com.common_library.library.entities.UserModel;
import com.user_service_api.user_service.common.dtos.UpdateUser;

public interface UserService {
    UserModel getUser(String id,Long userId);
    void updateUser(String id,UpdateUser updateUser,Long userId);
    void deleteUser(String id,Long userId);
}
