package com.ifind.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author 易某某
 * @date 2020/3/20 13:24
 *
 */
@Data
public class Bill implements Serializable {

    private static final long serialVersionUID = 1053759129019086114L;

    /**
     * 数据id
     */
    private Integer id;
    /**
     * 票据编号
     */
    private String billNo;
    /**
     * 承兑银行
     */
    private String bank;
    /**
     * 贴现率
     */
    private BigDecimal wightedAverageYield;
    /**
     * 票面金额
     */
    private BigDecimal faceBillAmt;
    /**
     * 到期日
     */
    private String repairDate;
}
