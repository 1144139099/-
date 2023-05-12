package com.hlh.contentsmart.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author w2gd
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAddBounsDto {
    private Integer userId;
    private Integer bonus;
}
