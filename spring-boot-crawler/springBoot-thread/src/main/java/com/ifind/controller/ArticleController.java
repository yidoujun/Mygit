package com.ifind.controller;

import com.ifind.entity.Article;
import com.ifind.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping("/findAll")
    public List<Article> findAllBySingleThread() {
        List<Article> articleList = articleService.findAllBySingleThread();
        return articleList;
    }

    @RequestMapping("/findAll1")
    public List<Article> findAllByMultithreading() {
        List<Article> articleList = articleService.findAllByMultithreading();
        return articleList;
    }

}
