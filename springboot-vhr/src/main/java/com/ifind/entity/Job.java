package com.ifind.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yidujun
 * @date 2020/4/13 9:29
 *
 */
@Data
public class Job implements Serializable {
    private static final long serialVersionUID = 4589699808999073441L;

    /**
     * id
     */
    private Integer id;
    /**
     * 省份名字
     */
    private String provinceName;
    /**
     * 城市名字
     */
    private String cityName;
    /**
     * 省份包含数量
     */
    private Integer provinceNum;
    /**
     * 城市包含数量
     */
    private Integer cityNum;

}
