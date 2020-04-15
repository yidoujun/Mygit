package com.ifind.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 文章实体类
 *
 * @author 易都军
 * @date 2020/3/18 14:08
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Article implements Serializable {

    private static final long serialVersionUID = 4328878480225112440L;

    private Long id;
    private String articleTitle;
    private String articleAuthor;
    private String articlePub;
    private String articleReference;
    private String articleReadNum;
    private String articleLike;


}
