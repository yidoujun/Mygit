package com.eblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 路由控制类
 *
 * @author yidujun
 * @date 2020/4/30 14:13
 */
@Controller
public class RouterController {

    @RequestMapping({"", "/", "/index"})
    public String index() {
        return "index";
    }
}
