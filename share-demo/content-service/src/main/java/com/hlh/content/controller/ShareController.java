package com.hlh.content.controller;

import com.alibaba.fastjson.JSONObject;
import com.hlh.content.common.ResponseResult;
import com.hlh.content.domain.dto.ShareDto;
import com.hlh.content.domain.entity.Share;
import com.hlh.content.domain.entity.User;
import com.hlh.content.openfeign.UserService;
import com.hlh.content.servicec.ShareService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(value = "/shares")
public class ShareController {
    private final ShareService shareService;

    private final UserService userService;
    @GetMapping("{id}")
    public ResponseResult getShareById(@PathVariable Integer id){
        Share share = shareService.findById(id);
        Integer userId = share.getUserId();
        ResponseResult res = userService.getUser(userId);
        String jsonString = JSONObject.toJSONString(res.getData());
        JSONObject obj = JSONObject.parseObject(jsonString);
        User user = JSONObject.toJavaObject(obj, User.class);
        ShareDto shareDto = ShareDto.builder().share(share).nickName(user.getNickname()).avatar(user.getAvatar()).build();
        return ResponseResult.success(shareDto);
    }
}
