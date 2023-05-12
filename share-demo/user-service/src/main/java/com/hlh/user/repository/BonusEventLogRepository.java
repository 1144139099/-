package com.hlh.user.repository;

import com.hlh.user.domain.entity.BonusEventLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author hlh
 */
public interface BonusEventLogRepository extends JpaRepository<BonusEventLog, Integer> {
}
