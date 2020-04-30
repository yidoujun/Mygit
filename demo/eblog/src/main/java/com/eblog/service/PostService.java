package com.eblog.service;

import com.eblog.entity.Post;

import java.util.List;

public interface PostService {

    List<Post> getList(Integer pageNum, Integer pageSize);

    void initIndexWeekRank();
}
