package com.hlh.repair.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RepairDto {
    private Integer userId;
    private String title;
    private String author;
    private String cover;
    private String summary;
}
