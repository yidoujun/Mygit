package com.ifind.dao;

import com.ifind.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 文章数据操作接口
 *
 * @author yidujun
 * @date 2020/4/7 20:52
 */
@Repository
@Mapper
public interface ArticleDao {

    /**
     * 查询总数
     * @return
     */
    public Integer findCount();

    /**
     * 查询所有文章
     * @return
     */
    public List<Article> findAll();

    /**
     * 分页查询
     * @param offset
     * @param pageSize
     * @return
     */
    public List<Article> findByPage(Integer offset, Integer pageSize);
}
