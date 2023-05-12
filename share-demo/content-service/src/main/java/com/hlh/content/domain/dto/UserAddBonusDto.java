package com.hlh.content.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hlh
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAddBonusDto {
    private Integer userId;
    private Integer bonus;
}
