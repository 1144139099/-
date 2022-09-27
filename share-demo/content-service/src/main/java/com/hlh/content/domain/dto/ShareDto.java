package com.hlh.content.domain.dto;

import com.hlh.content.domain.entity.Share;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShareDto {
    private Share share;
    private String nickName;
    private String avatar;
}
