package com.hlh.content.repository;

import com.hlh.content.domain.dto.ShareVo;
import com.hlh.content.domain.entity.Share;
import feign.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import javax.websocket.server.PathParam;
import java.util.List;

public interface ShareRepository extends JpaRepository<Share, Integer>, JpaSpecificationExecutor<Share> {

    List<Share> findByShowFlagAndAuditStatus(Boolean showFlag, String auditStatus, Sort sort);
//    List<Share> findByUserId(Integer userId, Pageable pageable);
    Page<Share> findByUserId(Integer userId, Pageable pageable);
    @Query(value = "SELECT new com.hlh.content.domain.dto.ShareVo(s.title,s.cover,s.createTime) FROM Share s WHERE s.id IN (SELECT m.shareId FROM MidUserShare m WHERE m.userId = ?1) AND s.userId <> ?1")
    Page<ShareVo> findExchange(Integer userId, Pageable pageable);
}
