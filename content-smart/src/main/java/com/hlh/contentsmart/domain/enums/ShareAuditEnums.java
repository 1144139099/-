package com.hlh.contentsmart.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author w2gd
 */

@Getter
@AllArgsConstructor
public enum ShareAuditEnums {
    /**
     * 等待审核
     */
    NOT_YET,
    /**
     * 审核通过
     */
    PASS,
    /**
     * 审核不通过
     */
    REJECT

}
