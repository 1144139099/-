package com.hlh.content.repository;

import com.hlh.content.domain.entity.BonusEventLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BonusEventLogRepository extends JpaRepository<BonusEventLog, Integer> {
   List<BonusEventLog> findByUserId(int userId);
}
