package com.hlh.logintest.controller;

import cn.authing.core.auth.AuthenticationClient;
import cn.authing.core.graphql.GraphQLException;
import cn.authing.core.types.LoginByPhonePasswordInput;
import cn.authing.core.types.User;
import com.hlh.logintest.common.ResponseResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(value = "/users")
public class testController {
    AuthenticationClient authentication = new AuthenticationClient("6368b107484432f7d4db8652", "https://imqetirdvfuj-demo.authing.cn");
    @GetMapping("/test")
    public ResponseResult PhoneAndPasswordLogin() throws IOException, GraphQLException {
        User user = authentication.getCurrentUser().execute();
        return ResponseResult.success(user);
    }
}
