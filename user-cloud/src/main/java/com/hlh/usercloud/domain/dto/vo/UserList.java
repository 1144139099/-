package com.hlh.usercloud.domain.dto.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserList {
    private String id;
    private String phone;
    private String photo;
    private String name;
}
