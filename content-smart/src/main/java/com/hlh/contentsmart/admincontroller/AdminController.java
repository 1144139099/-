package com.hlh.contentsmart.admincontroller;

import com.hlh.contentsmart.auth.CheckAuthorization;
import com.hlh.contentsmart.auth.CheckLogin;
import com.hlh.contentsmart.common.ResponseResult;
import com.hlh.contentsmart.domain.dto.AuditShareDto;
import com.hlh.contentsmart.domain.entity.Share;
import com.hlh.contentsmart.service.ShareService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 审核
 * @author w2gd
 */
@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AdminController {

    private final ShareService shareService;
    @PostMapping("/verify")
    @CheckLogin
    @CheckAuthorization("admin")
    public ResponseResult verifyContent(@RequestBody AuditShareDto auditShareDto){
        Share share = shareService.auditShare(auditShareDto);

        return ResponseResult.success(share);
    }
}
