package com.hlh.usersmart.repository;

import com.hlh.usersmart.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author w2gd
 */
public interface UserRepository extends JpaRepository<User,Integer> {
    /**
     * 根据手机号和密码查找用户
     * @param mobile 手机号
     * @param password 密码
     * @return 用户
     */
    User findByMobileAndPassword(String mobile, String password);
}
