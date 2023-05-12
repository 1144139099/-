package com.hlh.repair.service;

import com.hlh.repair.domain.entity.Checkin;

import java.util.List;

public interface CheckService {
    /**
     * 根据用户ID返回shares
     * @param userId 用户ID
     * @return repairList
     */
    List<Checkin> getCheckByUserId(Integer userId);

    /**
     * 新增
     * @param check check
     * @return share
     */
    Checkin addCheck(Checkin check);

    Checkin findById(Integer id);
}
