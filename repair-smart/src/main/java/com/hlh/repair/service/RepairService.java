package com.hlh.repair.service;

import com.hlh.repair.domain.entity.Repair;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RepairService {
    /**
     * 根据审核状态显示查询
     * @param pageNum 当前页
     * @param pageSize 每页数量
     * @param status 审核状态
     * @return 分页数据
     */
    Page<Repair> getPageShareByAudit(int pageNum, int pageSize, String status);

    /**
     * 根据用户ID返回shares
     * @param userId 用户ID
     * @return repairList
     */
    List<Repair> getSharesByUserId(Integer userId);

    /**
     * 新增
     * @param repair repair
     * @return share
     */
    Repair addShare(Repair repair);

    Repair findById(Integer id);
}
