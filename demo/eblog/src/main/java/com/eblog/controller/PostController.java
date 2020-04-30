package com.eblog.controller;

import com.eblog.common.KeyConstant;
import com.eblog.common.result.CommonResult;
import com.eblog.entity.Post;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/post")
public class PostController extends BaseController{

    @RequestMapping("/list")
    public CommonResult<List<Post>> list(@RequestParam(value = "pageNum") Integer pageNum,
                                         @RequestParam(value = "pageSize") Integer pageSize) {

        List<Post> posts = postService.getList(pageNum, pageSize);

        return CommonResult.success(posts);
    }

    @RequestMapping("/hots")
    public CommonResult<List> hotPost() {
        // Ctrl + Alt + V 快速生成方法返回值类型
        Set<ZSetOperations.TypedTuple> lastWeekRank = redisUtil.getZSetRank(KeyConstant.POST_WEEK, 0, 6);

        List<Map<String, Object>> hostPots = new ArrayList<>();
        for (ZSetOperations.TypedTuple typedTuple : lastWeekRank) {
            Map<String, Object> map = new HashMap<>();
            map.put("comment_count", typedTuple.getScore());
            map.put("id", redisUtil.hget(KeyConstant.POST_INFO + typedTuple.getValue(), "post:id"));
            map.put("title", redisUtil.hget(KeyConstant.POST_INFO + typedTuple.getValue(), "post:title"));
            hostPots.add(map);
        }
        return CommonResult.success(hostPots);
    }
}
