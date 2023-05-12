package com.hlh.repair.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CheckDto {
    private Integer userId;
    private String address;
    private String nickname;
    private String reason;
}
