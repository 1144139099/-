package com.hlh.user.repository;

import com.hlh.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByMobileAndPassword(String mobile, String password);
}
