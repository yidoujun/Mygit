package com.ydj.demo.json;

/**
 * 异常码枚举类接口，使用接口统一自定义exception入参，避免开发随意添加异常码
 *
 * @author yidujun
 * @date 2020/4/17 10:31
 */
public interface CommonCode {

    String getSubCode();
    String getSubMsg();
}
