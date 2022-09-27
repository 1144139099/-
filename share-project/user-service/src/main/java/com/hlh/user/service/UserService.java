package com.hlh.user.service;

import com.hlh.user.domain.dto.UserDto;
import com.hlh.user.domain.entity.User;
import org.springframework.stereotype.Service;

/**
 * @author hlh
 */
@Service
public interface UserService {

    User findById(Integer id);

    User login(UserDto userDto);
}
