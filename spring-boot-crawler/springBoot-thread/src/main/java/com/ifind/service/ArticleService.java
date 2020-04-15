package com.ifind.service;

import com.ifind.entity.Article;

import java.util.List;

/**
 * 文章业务接口
 *
 * @author yidujun
 * @date 2020/4/7 20:49
 */
public interface ArticleService {

    /**
     * 单线程查询
     *
     * @return
     */
    List<Article> findAllBySingleThread();

    /**
     * 多线程查询
     *
     * @return
     */
    List<Article> findAllByMultithreading();
}
