package com.hlh.repair.repository;

import com.hlh.repair.domain.entity.Checkin;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CheckRepository extends JpaRepository<Checkin, Integer>, JpaSpecificationExecutor<Checkin> {
    /**
     * 根据用户ID返回数据
     *
     * @param userId     用户ID
     * @param createTime 创建时间
     * @return .
     */
    List<Checkin> findAllByUserId(Integer userId, Sort createTime);
}
