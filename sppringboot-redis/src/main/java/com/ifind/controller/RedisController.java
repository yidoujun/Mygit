package com.ifind.controller;

import com.ifind.entity.User;
import com.ifind.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@Slf4j
@RequestMapping("/redis")
@RestController
public class RedisController {

    private static long ExpireTime = 60;   // redis中存储的过期时间60s

    @Resource
    private RedisUtil redisUtil;

    @RequestMapping("/set")
    public boolean redisset(String key){
        User userEntity =new User();
        userEntity.setId(Long.valueOf(1));
        userEntity.setGuid(String.valueOf(1));
        userEntity.setName("zhangsan");
        userEntity.setAge(String.valueOf(20));
        userEntity.setCreateTime(new Date());

        return redisUtil.set(key,userEntity,ExpireTime);

//        return redisUtil.set(key,value);
    }

    @RequestMapping("/get")
    public Object redisget(String key){
        System.out.println(redisUtil.get(key));
        return redisUtil.get(key);
    }

    @RequestMapping("/expire")
    public boolean expire(String key){
        return redisUtil.expire(key, ExpireTime);
    }


}
