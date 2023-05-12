package com.hlh.repair.controller;

import com.hlh.repair.common.ResponseResult;
import com.hlh.repair.domain.dto.CheckDto;
import com.hlh.repair.domain.entity.Checkin;
import com.hlh.repair.service.CheckService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@Slf4j
@RequestMapping("/check")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CheckController {
    @Resource
    private final CheckService checkService;
    @GetMapping("{id}")
    public ResponseResult getCheckById(@PathVariable Integer id) {
        Checkin check = checkService.findById(id);
        return ResponseResult.success(check);
    }

    @GetMapping("/mycheck")
    public ResponseResult getCheckByUserId(@RequestParam int userId) {
        return ResponseResult.success(checkService.getCheckByUserId(userId));
    }
    @PostMapping("/add")
    public ResponseResult addCheck(@RequestBody CheckDto checkDto){
        Date date = new Date();
        Checkin checkin = Checkin.builder()
                .userId(checkDto.getUserId())
                .address(checkDto.getAddress())
                .reason(checkDto.getReason())
                .createTime(date)
                .nickname(checkDto.getNickname())
                .build();
        return ResponseResult.success(checkService.addCheck(checkin));
    }
}
