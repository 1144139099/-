package com.hlh.content.openfeign.fallback;

import com.hlh.content.common.ResponseResult;
import com.hlh.content.domain.entity.User;
import com.hlh.content.openfeign.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserServiceFallback implements UserService {
    @Override
    public ResponseResult getUser(int id) {
        log.info("fallback getUser");
        User user = User.builder().id(111).mobile("13900001111").nickname("降级方案返回用户").build();
        return ResponseResult.success(user);
    }
}
