package com.ydj.logback.controller;

import com.ydj.logback.common.CommonResult;
import com.ydj.logback.model.Article;
import com.ydj.logback.service.ArticleService;
import com.ydj.logback.service.InnerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping("/list")
    public CommonResult<List<Article>> queryArticlePage(@RequestParam("pageNum") Integer pageNum,
                                               @RequestParam("pageSize") Integer pageSize) {

        log.info(">>>> query team members by team id pageNum={} <<<<",pageNum);
        log.info(">>>> query team members by team id pageSize={} <<<<",pageSize);

        List<Article> list = articleService.list(pageNum, pageSize);
        return CommonResult.success(list);
    }
}
