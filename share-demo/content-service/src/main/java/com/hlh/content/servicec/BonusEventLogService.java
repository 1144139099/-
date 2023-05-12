package com.hlh.content.servicec;


import com.hlh.content.domain.entity.BonusEventLog;
import com.hlh.content.domain.entity.Share;

import java.util.List;

public interface BonusEventLogService {
    List<BonusEventLog> IntegralDetail(Integer userId);
}
