package com.hlh.content.controller;

import com.alibaba.fastjson.JSONObject;
import com.hlh.content.auth.CheckAuthorization;
import com.hlh.content.common.ResponseResult;
import com.hlh.content.domain.dto.*;
import com.hlh.content.domain.entity.BonusEventLog;
import com.hlh.content.domain.entity.Share;
import com.hlh.content.domain.entity.User;
import com.hlh.content.openfeign.UserService;
import com.hlh.content.servicec.BonusEventLogService;
import com.hlh.content.servicec.ShareService;
import com.hlh.content.utils.JwtOperator;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(value = "/shares")
public class ShareController {
    private final ShareService shareService;
    private final JwtOperator jwtOperator;
    private final BonusEventLogService bonusEventLogService;
    private final UserService userService;

    @GetMapping("{id}")
    public ResponseResult getShareById(@PathVariable Integer id) {
        Share share = shareService.findById(id);
        Integer userId = share.getUserId();
        ResponseResult res = userService.getUser(userId);
        String jsonString = JSONObject.toJSONString(res.getData());
        JSONObject obj = JSONObject.parseObject(jsonString);
        User user = JSONObject.toJavaObject(obj, User.class);
        ShareDto shareDto = ShareDto.builder().share(share).nickName(user.getNickname()).avatar(user.getAvatar()).build();
        return ResponseResult.success(shareDto);
    }

    @CheckAuthorization("admin")
    @PostMapping("/audit")
    public ResponseResult auditShare(@RequestBody ShareAuditDto shareAuditDto) {
        log.info(shareAuditDto + ">>>>>>>>>>>>>>>>>>");
        Share share = shareService.auditShare(shareAuditDto);
        return ResponseResult.success(share);
    }

    @PostMapping("/exChange")
    public ResponseResult exChange(@RequestParam(required = true) Integer shareId,
                                   @RequestHeader(value = "Author", required = true) String token) {
        int userId = getUserIdFromToken(token);
        Share share = shareService.exChange(shareId,userId);
        return ResponseResult.success(share);
    }

    @PostMapping("/postnote")
    public ResponseResult Submission(@RequestBody NoteDto noteDto,
                                     @RequestHeader(value = "Authorization", required = false) String token){
        int userId = getUserIdFromToken(token);
        Share share = Share.builder().author(noteDto.getAuthor()).createTime(new Date()).title(noteDto.getTitle())
                .isOriginal(noteDto.getIsOriginal()).cover(noteDto.getCover()).summary(noteDto.getSummary())
                .userId(userId).showFlag(false).buyCount(0).downloadUrl(noteDto.getDownloadUrl()).price(0).updateTime(new Date()).auditStatus("NOT_YET").build();
        shareService.Submission(share);
        return ResponseResult.success("投稿成功");
    }

    @GetMapping("all")
    public ResponseResult findAll(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                  @RequestParam(required = false, defaultValue = "5") Integer pageSize,
                                  @RequestParam(required = false) String title,
                                  @RequestParam(required = false) String summary,
                                  @RequestHeader(value = "Authorization", required = false) String token) {
//        Page<Share> shares = shareService.findAll(pageNum);
        log.info("getSharesByPage");
        log.info(token);
        int userId = getUserIdFromToken(token);
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        log.info(title);
        ShareQueryDto shareQueryDto = ShareQueryDto.builder().title(title).summary(summary).build();
        Page<Share> pageList = shareService.findAll(pageable, shareQueryDto, userId);
        List<Share> dataList = pageList.getContent();
        return ResponseResult.success(dataList);
    }
    @GetMapping("/mypost")
    public ResponseResult myPost(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                 @RequestParam(required = false, defaultValue = "5") Integer pageSize,
                                 @RequestHeader(value = "Authorization", required = false) String token){
        int userId = getUserIdFromToken(token);
//        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<Share> shares = shareService.myPost(pageNum,pageSize,userId);
        return ResponseResult.success(shares);
    }

    @GetMapping("/myexchange")
    public ResponseResult getExchangeRecord(@RequestHeader(name = "X-Token") String token,
                                            @RequestParam(required = false,defaultValue = "0") Integer pageNum,
                                            @RequestParam(required = false,defaultValue = "5") Integer pageSize) {
        Integer userId = getUserIdFromToken(token);
        return ResponseResult.success(shareService.getExchangeRecord(pageNum,pageSize,userId));
    }
    @GetMapping("/jfdetail")
    public ResponseResult IntegralDetail(@RequestParam Integer userId){
        List<BonusEventLog> bonusEventLog = bonusEventLogService.IntegralDetail(userId);
        return ResponseResult.success(bonusEventLog);
    }

    private int getUserIdFromToken(String token) {
        int userId = 0;
        if (token != null) {
            Claims claims = jwtOperator.getClaimsFromToken(token);
            log.info(claims.toString());
            userId = (Integer) claims.get("id");
        } else {
            log.info("没有token");
        }
        return userId;
    }
}
