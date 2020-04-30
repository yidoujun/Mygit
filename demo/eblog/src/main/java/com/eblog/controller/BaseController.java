package com.eblog.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eblog.service.PostService;
import com.eblog.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestUtils;

import javax.servlet.http.HttpServletRequest;

public class BaseController {

    @Autowired
    HttpServletRequest request;

    @Autowired
    PostService postService;

    @Autowired
    RedisUtil redisUtil;


    public Page getPage() {
        int pageNum = ServletRequestUtils.getIntParameter(request, "pageNum", 1);
        int pageSize = ServletRequestUtils.getIntParameter(request, "pageSize",10);
        return new Page(pageNum, pageSize);
    }

}
