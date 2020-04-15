package com.ifind.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * 线程配置类
 *
 * @author yidujun
 * @date 2020/4/7 20:43
 */
@Configuration
@ImportResource(locations = {"classpath:spring-thread.xml"})
@MapperScan(value = "com.ifind.dao")
public class ThreadConfig {
}
