package com.hlh.content.servicec.impl;

import com.alibaba.fastjson.JSONObject;
import com.hlh.content.common.ResponseResult;
import com.hlh.content.domain.dto.*;
import com.hlh.content.domain.entity.MidUserShare;
import com.hlh.content.domain.entity.Share;
import com.hlh.content.domain.entity.User;
import com.hlh.content.enums.AuditStatusEnum;
import com.hlh.content.openfeign.UserService;
import com.hlh.content.repository.MidUserShareRepository;
import com.hlh.content.repository.ShareRepository;
import com.hlh.content.repository.UserRepository;
import com.hlh.content.servicec.MidUserShareService;
import com.hlh.content.servicec.ShareService;
import io.jsonwebtoken.security.SecurityException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShareServiceImpl implements ShareService {
    private final ShareRepository shareRepository;
    private final MidUserShareRepository midUserShareRepository;
    private final MidUserShareService midUserShareService;
    private final RocketMQTemplate rocketMQTemplate;
    private final UserService userService;
    private final UserRepository userRepository;

    @Override
    public Share findById(Integer id) {
        return shareRepository.findById(id).orElse(null);
    }

    @Override
    public Share auditShare(ShareAuditDto shareAuditDto) {
        Share share = shareRepository.findById(shareAuditDto.getId()).orElse(null);

        if (!Objects.equals("NOT_YET", share.getAuditStatus())) {
            throw new IllegalArgumentException("参数非法！该分享已审核！");
        }
        share.setAuditStatus(shareAuditDto.getAuditStatusEnum().toString());
        share.setReason(shareAuditDto.getReason());
        share.setShowFlag(shareAuditDto.getShowFlag());
        Share newShare = shareRepository.saveAndFlush(share);
        midUserShareService.insert(
                MidUserShare.builder()
                        .shareId(newShare.getId())
                        .userId(newShare.getUserId())
                        .build()
        );
        if (AuditStatusEnum.PASS.equals(shareAuditDto.getAuditStatusEnum())) {
            rocketMQTemplate.convertAndSend(
                    "add-bonus",
                    UserAddBonusDto.builder()
                            .userId(share.getUserId())
                            .bonus(50)
                            .build());
        }
        return newShare;

//        return shareRepository.saveAndFlush(share);
    }

    @Override
    public Page<Share> findAll(Pageable pageable, ShareQueryDto shareQueryDto, Integer userId) {
        Page<Share> shares = shareRepository.findAll((Specification<Share>) (root, query, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            if (null != shareQueryDto.getTitle() && !"".equals(shareQueryDto.getTitle())) {
                list.add(criteriaBuilder.like(root.get("title").as(String.class), "%" + shareQueryDto.getTitle() + "%"));
            }
            if (null != shareQueryDto.getSummary() && !"".equals(shareQueryDto.getSummary())) {
                list.add(criteriaBuilder.like(root.get("summary").as(String.class), "%" + shareQueryDto.getSummary() + "%"));
            }
            Predicate[] p = new Predicate[list.size()];
            return criteriaBuilder.and(list.toArray(p));
        }, pageable);
        List<Share> shareDeal;
        if (userId == null) {
            shareDeal = shares.stream().peek(share -> share.setDownloadUrl(null)).collect(Collectors.toList());
        } else {
            shareDeal = shares.stream().peek(share -> {
                MidUserShare midUserShare = midUserShareRepository.findByUserIdAndShareId(userId, share.getId());
                if (midUserShare == null) {
                    share.setDownloadUrl(null);
                }
            }).collect(Collectors.toList());
        }
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), shareDeal.size());
//        return shareRepository.findAll(pageable);
        return new PageImpl<>(shareDeal.subList(start, end), pageable, shareDeal.size());
    }

    @Override
    public Share exChange(Integer shareId, Integer userId) {
        Share share = shareRepository.findById(shareId).orElse(null);
        if (share != null) {
            int price = share.getPrice();
            log.info(String.valueOf(price));
            MidUserShare midUserShare = midUserShareRepository.findByUserIdAndShareId(userId, shareId);
            if (midUserShare != null) {
                log.info(midUserShare.toString());
                throw new IllegalArgumentException("该资源只能兑换一次");
            } else {
//                ResponseResult res = userService.getUser(userId);
//                String jsonString = JSONObject.toJSONString(res.getData());
//                JSONObject obj = JSONObject.parseObject(jsonString);
//                User user = JSONObject.toJavaObject(obj, User.class);
                User user = userRepository.findById(userId).orElse(null);
                int userCount = user.getBonus();
                if (price < userCount){
                    rocketMQTemplate.convertAndSend(
                            "subtract-bonus",
                            UserAddBonusDto.builder()
                                    .userId(userId)
                                    .bonus(price)
                                    .build());
                    share.setBuyCount(share.getBuyCount() + 1);
                    Share newShare = shareRepository.saveAndFlush(share);
                    midUserShareService.insert(
                            MidUserShare.builder()
                                    .shareId(newShare.getId())
                                    .userId(userId)
                                    .build());
                    return newShare;
                }else{
                    throw new SecurityException("积分不足");
                }
            }
        } else {
            throw new IllegalArgumentException("该资源不存在");
        }
    }

    @Override
    public Share Submission(Share share) {
        return shareRepository.save(share);
    }

    @Override
    public Page<Share> myPost(Integer pageNum,Integer pageSize, Integer userId) {
        Pageable pageable = PageRequest.of(pageNum,pageSize, Sort.by(Sort.Direction.DESC,"createTime"));
        Page<Share> shares = shareRepository.findByUserId(userId,pageable);
        return shares;
    }

    @Override
    public Page<ShareVo> getExchangeRecord(Integer pageNum, Integer pageSize, Integer userId) {
        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by("createTime").descending());
        return shareRepository.findExchange(userId,pageable);
    }

//    @Override
//    public List<Share> mySubmission(Integer userId) {
//        List<MidUserShare> midUserShare = midUserShareRepository.findByUserId(userId);
//        List<Integer> list;
//        list.add(midUserShare)
//    }
}
