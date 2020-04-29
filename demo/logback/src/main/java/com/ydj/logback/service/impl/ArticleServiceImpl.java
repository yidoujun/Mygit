package com.ydj.logback.service.impl;

import com.ydj.logback.dao.ArticleDao;
import com.ydj.logback.model.Article;
import com.ydj.logback.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("articleService")
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDao articleDao;

    @Override
    public List<Article> list(Integer pageNum, Integer pageSize) {
        return articleDao.list(pageNum, pageSize);
    }
}
