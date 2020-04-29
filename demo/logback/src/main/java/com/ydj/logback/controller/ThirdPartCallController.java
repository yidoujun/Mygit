package com.ydj.logback.controller;

import com.ydj.logback.aop.ThirdPart;
import com.ydj.logback.common.CommonResult;
import com.ydj.logback.service.CommonService;
import com.ydj.logback.service.ThirdPartCallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author yidujun
 * @date 2020/4/29 20:00
 */
@RestController
@RequestMapping("/third")
public class ThirdPartCallController {

    @Autowired
    private ThirdPartCallService thirdPartCallService;

    @Autowired
    private CommonService commonService;

    @GetMapping("/test")
    @ThirdPart
    public CommonResult<List<LocalDateTime>> test(@RequestParam("id") Integer id,
                                                  @RequestParam("name") String name,
                                                  @RequestParam("age") Integer age){

        List<LocalDateTime> list = commonService.thirdMethod(id, name, age);

        return CommonResult.success(list);
    }

}
