package com.hlh.user.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.hlh.user.common.ResponseResult;
import com.hlh.user.common.ResultCode;
import com.hlh.user.domain.dto.UserDto;
import com.hlh.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(value = "/users")
public class UserController {
    private final UserService userService;

    @GetMapping("{id}")
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
        return ResponseResult.success(userService.login(userDto));
    }
}
