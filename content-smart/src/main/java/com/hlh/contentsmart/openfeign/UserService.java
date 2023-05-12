package com.hlh.contentsmart.openfeign;

import com.hlh.contentsmart.common.ResponseResult;
import com.hlh.contentsmart.domain.dto.UserProfileAuditDto;
import com.hlh.contentsmart.openfeign.fallback.UserServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.web.bind.annotation.*;

/**
 * @author w2gd
 */
@FeignClient(value = "user-service",path = "/users",fallbackFactory = UserServiceFallbackFactory.class)
// @FeignClient(value = "user-service",path = "/users",fallback = UserServiceFallback.class)
public interface UserService {
    /**
     * 调用用户服务
     * @param id id
     * @return User
     */
    @GetMapping("{id}")
    ResponseResult getUser(@PathVariable(value = "id") Integer id, @RequestHeader(name = "X-Token") String token);

    @PostMapping("/update")
    ResponseResult auditUserData(@RequestBody UserProfileAuditDto userProfileAuditDto, @RequestHeader(name = "X-Token") String token);
}
