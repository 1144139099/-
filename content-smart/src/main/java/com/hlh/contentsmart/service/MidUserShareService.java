package com.hlh.contentsmart.service;


import com.hlh.contentsmart.domain.entity.MidUserShare;

/**
 * @author w2gd
 */
public interface MidUserShareService {
    /**
     * 新增
     *
     * @param midUserShare 入参
     */
    void insert(MidUserShare midUserShare);

    /**
     * 根据userID 和 shareID 查询记录
     * @param userId 用户id
     * @param shareId shareId
     * @return 记录
     */
    MidUserShare selectRecordWithUserIdAndShareId(int userId,int shareId);
}
