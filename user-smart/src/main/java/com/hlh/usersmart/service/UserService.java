package com.hlh.usersmart.service;

import com.hlh.usersmart.domain.entity.BonusEventLog;
import com.hlh.usersmart.domain.entity.User;
import com.hlh.usersmart.domain.entity.dto.UserDto;
import com.hlh.usersmart.domain.entity.dto.UserProfileAuditDto;
import org.springframework.data.domain.Page;


/**
 * @author w2gd
 */
public interface UserService {
    /**
     *  根据id找用户
     * @param id id
     * @return user
     */
    User findById(Integer id);

    /**
     * 用户登录方法
     * @param userDto 。
     * @return token
     */
    User login(UserDto userDto);

    /**
     * 修改个人信息
     * @param userProfileAuditDto 新的用户个人信息
     * @return  新用户信息
     */
    User auditProfile(UserProfileAuditDto userProfileAuditDto);

    /**
     * 获取积分明细
     * @param userId 用户id
     * @param pageNum 分页
     * @param pageSize 分页
     * @return 分页积分明细
     */
    Page<BonusEventLog> getBonusRecord(Integer userId, Integer pageNum, Integer pageSize);
}
