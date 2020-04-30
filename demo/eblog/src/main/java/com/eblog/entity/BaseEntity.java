package com.eblog.entity;

import lombok.Data;

import java.util.Date;

/**
 * 基础实体类
 *
 * @author yidujun
 * @date 2020/4/30 11:12
 */
@Data
public class BaseEntity {

    private Long id;
    private Date created;
    private Date modified;
}
