package com.auth_service_api.auth_service.repository;

import com.common_library.library.entities.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel,Long> {
    Optional<UserModel> findByEmail(String email);
}
