package com.hlh.contentsmart.repository;

import com.hlh.contentsmart.domain.entity.MidUserShare;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author w2gd
 */
public interface MidUserShareRepository extends JpaRepository<MidUserShare,Integer> {

    MidUserShare findByUserIdAndAndShareId(int userId, int shareId);
}
