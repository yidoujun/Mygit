package com.eblog.mapper;

import com.eblog.entity.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@Mapper
public interface PostMapper {


    List<Post> getList(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    List<Post> getListByWeek();
}
