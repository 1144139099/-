package com.hlh.repair.controller;

import com.hlh.repair.common.ResponseResult;
import com.hlh.repair.common.ResultCode;
import com.hlh.repair.domain.dto.RepairDto;
import com.hlh.repair.domain.entity.Repair;
import com.hlh.repair.service.RepairService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author w2gd
 */
@RestController
@Slf4j
@RequestMapping("/repair")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RepairController {
    private final RepairService repairService;
    final Integer MAX_SIZE = 50;



    @GetMapping("{id}")
    public ResponseResult getShareById(@PathVariable Integer id) {
        Repair share = repairService.findById(id);
        return ResponseResult.success(share);
    }


    /**
     * 根据审核状态返回shares列表
     * @param pageNum 页
     * @param pageSize 数
     * @param status 状态
     * @return sharesList
     */
    @GetMapping("/status")
    public ResponseResult getRepairByAuditStatus(@RequestParam int pageNum,@RequestParam int pageSize, @RequestParam String status) {
        return ResponseResult.success(repairService.getPageShareByAudit(pageNum,pageSize,status));
    }

    /**
     * 根据用户id 返回shares
     * @param userId yhu id
     * @return .
     */
    @GetMapping("/myRepair")
    public ResponseResult getSharesByUserId(@RequestParam int userId) {
        return ResponseResult.success(repairService.getSharesByUserId(userId));
    }
    @PostMapping("/add")
    public ResponseResult addShares(@RequestBody RepairDto repairDto){
        Date date = new Date();
        Repair repair = Repair.builder()
                .userId(repairDto.getUserId())
                .title(repairDto.getTitle())
                .createTime(date)
                .author(repairDto.getAuthor())
                .cover(repairDto.getCover())
                .summary(repairDto.getSummary())
                .status("NOT_YET")
                .reason("")
                .build();
        return ResponseResult.success(repairService.addShare(repair));
    }
}
