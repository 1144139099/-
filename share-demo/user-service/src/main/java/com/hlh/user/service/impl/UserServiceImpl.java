package com.hlh.user.service.impl;

import com.hlh.user.domain.dto.UserDto;
import com.hlh.user.domain.entity.User;
import com.hlh.user.repository.UserRepository;
import com.hlh.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;


    @Override
    public User findById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User login(UserDto userDto) {
        return userRepository.findByMobileAndPassword(userDto.getMobile(), userDto.getPassword());
    }
}
