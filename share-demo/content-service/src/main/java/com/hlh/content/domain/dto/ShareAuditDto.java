package com.hlh.content.domain.dto;


import com.hlh.content.enums.AuditStatusEnum;
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
public class ShareAuditDto {
    private Integer id;
    private AuditStatusEnum auditStatusEnum;
    private String reason;
    private Boolean showFlag;
}
