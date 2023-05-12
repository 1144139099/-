package com.hlh.contentsmart.domain.dto;

import com.hlh.contentsmart.domain.enums.ShareAuditEnums;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 审核dto
 *  2022-10-04-17-57
 * @author wd
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuditShareDto {
    private Integer id;
    private ShareAuditEnums shareAuditEnums;
    private String reason;
    private Boolean showFlag;
}