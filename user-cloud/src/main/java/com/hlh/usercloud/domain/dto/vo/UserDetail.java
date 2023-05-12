package com.hlh.usercloud.domain.dto.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDetail {
    private String id;
    private String gender;
    private String name;
    private String photo;
}
