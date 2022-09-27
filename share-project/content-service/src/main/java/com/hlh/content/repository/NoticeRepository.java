package com.hlh.content.repository;

import com.hlh.content.domain.entity.Notice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Integer> {
    List<Notice> findByShowFlag(Boolean showFlag, Sort sort);
}
