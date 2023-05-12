package com.hlh.repair.repository;

import com.hlh.repair.domain.entity.Repair;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author hlh
 */
public interface RepairRepository extends JpaRepository<Repair, Integer>, JpaSpecificationExecutor<Repair> {
    /**
     * 根据审核状态显示查询
     *
     * @param auditStatus 审核状态
     * @param pageRequest 分页
     * @return 分页Share
     */
    Page<Repair> findByStatus(String auditStatus, PageRequest pageRequest);

    /**
     * 根据用户ID返回数据
     *
     * @param userId     用户ID
     * @param createTime 创建时间
     * @return .
     */
    List<Repair> findAllByUserId(Integer userId, Sort createTime);
}
