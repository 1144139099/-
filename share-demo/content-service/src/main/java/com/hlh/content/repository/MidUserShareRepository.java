package com.hlh.content.repository;

import com.hlh.content.domain.entity.MidUserShare;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author hlh
 */
public interface MidUserShareRepository extends JpaRepository<MidUserShare, Integer> {
    MidUserShare findByUserIdAndShareId(int userId, int shareId);
    List<MidUserShare> findByUserId(int userId);
}
