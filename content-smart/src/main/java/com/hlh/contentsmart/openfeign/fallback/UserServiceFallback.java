package com.hlh.contentsmart.openfeign.fallback;

import com.hlh.contentsmart.common.ResponseResult;
import com.hlh.contentsmart.domain.dto.UserProfileAuditDto;
import com.hlh.contentsmart.domain.entity.User;
import com.hlh.contentsmart.openfeign.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 * @author w2gd
 */
@Slf4j
@Component
public class UserServiceFallback implements UserService {


    @Override
    public ResponseResult getUser(Integer id, String token) {
        log.info("getUser fallback");
        //log.info(token);
        User user = User.builder().avatar("logo.png").nickname("降级方案用户").mobile("10000000000").build();
        return ResponseResult.success(user);
    }

    @Override
    public ResponseResult auditUserData(UserProfileAuditDto userProfileAuditDto, String token) {
        return null;
    }
}
