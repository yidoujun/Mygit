package com.ifind.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 文章实体类
 *
 * @author yidujun
 * @date 2020/4/7
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Article {
    /**
     * 主键id
     */
    private Integer id;

    /**
     * 文章标题
     */
    private String articleTitle;

    /**
     * 文章作者
     */
    private String articleAuthor;

    /**
     * 文章被引次数
     */
    private String articleReference;

    /**
     * 文章阅读量
     */
    private String articleReadNum;

    /**
     * 文章收藏数
     */
    private String articleLike;
}
