package com.hlh.contentsmart.domain.dto;

import com.hlh.contentsmart.domain.entity.Share;
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
public class ShareDto {
    private Share share;
    private String nickName;
    private String avatar;
}
