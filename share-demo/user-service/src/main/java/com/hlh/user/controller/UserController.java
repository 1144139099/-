package com.hlh.user.controller;

import com.google.common.collect.Maps;
import com.hlh.user.auth.CheckLogin;
import com.hlh.user.common.ResponseResult;
import com.hlh.user.common.ResultCode;
import com.hlh.user.domain.dto.TokenDto;
import com.hlh.user.domain.dto.UserDto;
import com.hlh.user.service.UserService;
import com.hlh.user.utils.JwtOperator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(value = "/users")
public class UserController {
    private final UserService userService;
    private final JwtOperator jwtOperator;

    @GetMapping("{id}")
//    @CheckLogin
//    @SentinelResource(value = "getUserById", blockHandler = "getUserByIdBlock")
    public ResponseResult getUserById(@PathVariable Integer id){
//        try{
//            Thread.sleep(3000);
//        }catch (InterruptedException e){
//            throw new RuntimeException(e);
//        }
        val user = userService.findById(id);
        if (user != null){
            return ResponseResult.success(user);
        }else {
            return ResponseResult.failure(ResultCode.RESULT_CODE_DATA_NONE);
        }
//        return ResponseResult.success(userService.findById(id));
    }
//    public ResponseResult getUserByIdBlock(BlockException exception){
//        log.info("接口被限流");
//        log.info(exception.toString());
//        return ResponseResult.failure(ResultCode.INTERFACE_EXCEED_LOAD);
//    }

    @PostMapping(value = "/login")
    public ResponseResult login(@RequestBody UserDto userDto){
        val user = userService.login(userDto);
        TokenDto tokenDto = new TokenDto();
        tokenDto.setId(user.getId());
        HashMap<String, Object> map = Maps.newHashMap();
        map.put("id", user.getId());
        map.put("role", user.getRoles());
        String token = jwtOperator.generateToken(map);
        tokenDto.setToken(token);
        return ResponseResult.success(tokenDto);
    }
}
