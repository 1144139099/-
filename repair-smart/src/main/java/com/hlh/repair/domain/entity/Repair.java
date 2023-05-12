package com.hlh.repair.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author hlh
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Repair {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;

    private String title;

    private Date createTime;

    private String author;

    private String cover;

    private String summary;

    private String status;

    private String reason;
}
