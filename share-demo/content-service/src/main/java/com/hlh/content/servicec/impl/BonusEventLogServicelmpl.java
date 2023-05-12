package com.hlh.content.servicec.impl;


import com.hlh.content.domain.entity.BonusEventLog;
import com.hlh.content.domain.entity.Share;
import com.hlh.content.repository.BonusEventLogRepository;
import com.hlh.content.servicec.BonusEventLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BonusEventLogServicelmpl implements BonusEventLogService {
    private final BonusEventLogRepository bonusEventLogRepository;


    @Override
    public List<BonusEventLog> IntegralDetail(Integer userId) {
        List<BonusEventLog> list = bonusEventLogRepository.findByUserId(userId);
        return list;
    }
}
