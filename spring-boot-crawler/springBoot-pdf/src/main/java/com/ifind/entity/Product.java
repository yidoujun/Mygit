package com.ifind.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 实体类
 *
 * @author 易都军
 * @date 2020/3/24 13:45
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Product {
    private String productName;
    private String productCode;
    private float price;
}
