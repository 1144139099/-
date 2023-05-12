package com.hlh.usercloud.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String gender;
    private String email;
    private String id;
    private String phone;
    private String name;
    private String photo;
    private List<String> roleList;
    private String sno;
    private String classes;
    private String college;
    private String address;
    private String teacherPhone;
    private String teacherName;
    private String yuanZhangId;
    private String fuDaoYuanId;
    private String teacherId;
}
