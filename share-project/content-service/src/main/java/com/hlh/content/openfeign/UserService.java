package com.hlh.content.openfeign;

import com.hlh.content.common.ResponseResult;
import com.hlh.content.openfeign.fallback.UserServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "user-service", path = "/users", fallback = UserServiceFallback.class)
public interface UserService {
    @GetMapping("{id}")
    ResponseResult getUser(@PathVariable(value = "id") int id);
}
