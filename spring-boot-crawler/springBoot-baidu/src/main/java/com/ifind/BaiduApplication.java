package com.ifind;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan("com.ifind.dao")
public class BaiduApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaiduApplication.class, args);
    }
}
