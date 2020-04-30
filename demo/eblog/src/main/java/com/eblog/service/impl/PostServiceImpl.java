package com.eblog.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.eblog.common.KeyConstant;
import com.eblog.entity.Post;
import com.eblog.mapper.PostMapper;
import com.eblog.service.PostService;
import com.eblog.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("postService")
public class PostServiceImpl implements PostService {

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public List<Post> getList(Integer pageNum, Integer pageSize) {
        return postMapper.getList(pageNum, pageSize);
    }

    /**
     * 初始化首页的周评论排行榜
     */
    @Override
    public void initIndexWeekRank() {
        List<Post> last7DayPosts = postMapper.getListByWeek();
        for (Post post : last7DayPosts) {
            String key = KeyConstant.POST_COMMENT + DateUtil.format(post.getCreated(), DatePattern.PURE_DATE_FORMAT);

            redisUtil.zSet(key, post.getId(), post.getCommentCount());

            /**
             * 7 天后自动过期
             * 例:
             * 今日：18号     发表日：15日
             * 过期剩余时间：7 - (18-15) = 4
             */
            long between = DateUtil.between(new Date(), post.getCreated(), DateUnit.DAY);
            // 有效时间
            long expireTime = (7 - between) * 24 * 60 * 60;

            redisUtil.expire(key, expireTime);

            // 缓存文章的一些基本信息（id，标题，评论数量，作者）
            this.hashCachePostIdAndTitle(post, expireTime);
        }

        // 做并集
        this.zunionAndStoreLast7DayForWeekRank();
    }

    /**
     * 合并本周每日评论数量操作
     */
    private void zunionAndStoreLast7DayForWeekRank() {

        String currentKey = KeyConstant.POST_COMMENT + DateUtil.format(new Date(), DatePattern.PURE_DATE_FORMAT);

        String destKey = KeyConstant.POST_WEEK;
        List<String> otherKeys = new ArrayList<>();
        for (int i = -6; i < 0; i++) {
            String temp = KeyConstant.POST_COMMENT +
                    DateUtil.format(DateUtil.offsetDay(new Date(), i), DatePattern.PURE_DATE_FORMAT);
            otherKeys.add(temp);
        }
        redisUtil.zUnionAndStore(currentKey, otherKeys, destKey);
    }

    /**
     * 缓存文章的基本信息
     *
     * @param post          缓存对象
     * @param expireTime    过期时间
     */
    private void hashCachePostIdAndTitle(Post post, long expireTime) {
        String key = KeyConstant.POST_INFO + post.getId();
        boolean hasKey = redisUtil.hasKey(key);
        if (!hasKey) {
            redisUtil.hset(key, "post:id", post.getId(), expireTime);
            redisUtil.hset(key, "post:title", post.getTitle(), expireTime);
            redisUtil.hset(key, "post:commentCount", post.getCommentCount(), expireTime);
            redisUtil.hset(key, "post:viewCount", post.getViewCount(), expireTime);
        }

    }
}
