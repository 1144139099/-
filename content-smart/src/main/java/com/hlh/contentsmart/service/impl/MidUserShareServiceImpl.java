package com.hlh.contentsmart.service.impl;

import com.hlh.contentsmart.domain.entity.MidUserShare;
import com.hlh.contentsmart.repository.MidUserShareRepository;
import com.hlh.contentsmart.service.MidUserShareService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author w2gd
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MidUserShareServiceImpl implements MidUserShareService {
    private final MidUserShareRepository midUserShareRepository;

    @Override
    public void insert(MidUserShare midUserShare) {
        midUserShareRepository.save(midUserShare);
    }

    /**
     * 根据userID 和 shareID 查询记录
     *
     * @param userId  用户id
     * @param shareId shareId
     * @return 记录
     */
    @Override
    public MidUserShare selectRecordWithUserIdAndShareId(int userId, int shareId) {
        return midUserShareRepository.findByUserIdAndAndShareId(userId,shareId);
    }
}
