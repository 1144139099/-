package com.hlh.usercloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

/**
 * @author hlh
 */
@RestController
public class CallbackController {
    @GetMapping("/callback")
    public String callback(@PathParam("code") String code){
        System.out.println(code);
        return code;
    }
}
