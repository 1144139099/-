package com.hlh.usersmart.repository;

import com.hlh.usersmart.domain.entity.BonusEventLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author w2gd
 */
public interface BonusEventLogRepository extends JpaRepository<BonusEventLog,Integer> {

    /**
     * 查询积分明细
     * @param userId 用户id
     * @param pageable 分页
     * @return 分页积分明细
     */
    Page<BonusEventLog> findByUserId(Integer userId, Pageable pageable);
}
