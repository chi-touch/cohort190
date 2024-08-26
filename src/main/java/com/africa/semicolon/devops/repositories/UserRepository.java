package com.africa.semicolon.devops.repositories;

import com.africa.semicolon.devops.models.DevUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<DevUser, Long> {
     DevUser findDevUserByUserNameIgnoreCase(String userName);
}
