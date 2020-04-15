package com.ifind.entity;

import lombok.Data;

/**
 *
 * @author 易都军
 * @date 2020/3/24 16:15
 */
@Data
public class Product {
    private String productName;

    private String productCode;

    private float price;

    public Product(String productName,String productCode,float price){
        this.productName = productName;
        this.productCode = productCode;
        this.price = price;
    }

}
