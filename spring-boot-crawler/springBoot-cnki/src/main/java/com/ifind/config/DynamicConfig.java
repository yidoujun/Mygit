package com.ifind.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 爬虫动态设置
 *
 * @author 易都军
 * @date 2020/3/19 11:07
 */
@Configuration
public class DynamicConfig {

    /**
     * 爬虫driver配置
     */
    public static String WEBDRIVER;

    @Value("${webChromDriver}")
    public void setWebDriver(String webDriver) { this.WEBDRIVER = webDriver; }



}
