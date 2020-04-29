package com.ydj.logback.service;

import com.ydj.logback.model.Article;

import java.util.List;

public interface ArticleService {

    List<Article> list(Integer pageNum, Integer pageSize);
}
