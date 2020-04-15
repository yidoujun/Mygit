//package com.ifind.dao;

import com.ifind.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ArticleDao {


    /**
     * 保存数据
     * @param article
     */
    void insertArticle(Article article);
}
