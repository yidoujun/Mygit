package com.ydj.logback.dao;

import com.ydj.logback.model.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
@Mapper
public interface ArticleDao {

    List<Article> list(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);
}
