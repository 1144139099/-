package com.hlh.user.listener;

import com.hlh.user.domain.dto.UserAddBonusDto;
import com.hlh.user.domain.entity.BonusEventLog;
import com.hlh.user.domain.entity.User;
import com.hlh.user.repository.BonusEventLogRepository;
import com.hlh.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RocketMQMessageListener(consumerGroup = "consumer", topic = "subtract-bonus")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SubtractBonusListener implements RocketMQListener<UserAddBonusDto> {
    private final UserRepository userRepository;
    private final BonusEventLogRepository bonusEventLogRepository;

    @Override
    public void onMessage(UserAddBonusDto userAddBonusDto) {
        Integer userId = userAddBonusDto.getUserId();
        User user = userRepository.findById(userId).orElse(null);
        if (user != null){
            user.setBonus(user.getBonus() - userAddBonusDto.getBonus());
            userRepository.saveAndFlush(user);
        }
        bonusEventLogRepository.save(BonusEventLog.builder()
                .userId(userId)
                .value(userAddBonusDto.getBonus())
                .event("CONTRIBUTE")
                .createTime(new Date())
                .description("兑换资源")
                .build());
    }
}
