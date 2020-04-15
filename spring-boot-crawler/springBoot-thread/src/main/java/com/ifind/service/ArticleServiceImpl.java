package com.ifind.service;

import cn.hutool.core.collection.CollUtil;
import com.ifind.dao.ArticleDao;
import com.ifind.entity.Article;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 文章业务实现类
 *
 * @author yidujun
 * @date 2020/4/7 20:52
 */
@Service("articleService")
public class ArticleServiceImpl implements ArticleService{

    @Autowired
    private ArticleDao dao;

    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleServiceImpl.class);

    /**
     * 单线程查询
     *
     * @return
     */
    @Override
    public List<Article> findAllBySingleThread() {
        LOGGER.error("start....");
        Long start = System.currentTimeMillis();
        Integer count = dao.findCount();
        List<Article> list = dao.findAll();
        LOGGER.error("查询全部消耗了:" + (System.currentTimeMillis() - start) + "s");
        LOGGER.error("end....");
        return list;
    }

    /**
     * 多线程查询
     *
     * @return
     */
    @Override
    public List<Article> findAllByMultithreading() {
        LOGGER.error("start....");
        Long start = System.currentTimeMillis();
        List<Article> articles = new ArrayList<Article>();
        try {
            Integer total = dao.findCount();
            int threadSize = total / 10;    // 每页大小
            int threadNum = total % threadSize == 0 ? total / threadSize : total / threadSize + 1; //总页数
            ExecutorService exec = Executors.newFixedThreadPool(threadNum);     // threadNum线程数
            List<Callable<Integer>> tasks = new ArrayList<>();
            Callable<Integer> task = null;
            Map<Integer, List<Article>> map = new HashMap<>();
            for (int i = 1; i <= threadNum; i++) {
                final int pageNum = i;
                task = new Callable<Integer>() {
                    @Override
                    public Integer call() throws Exception {
                        List<Article> partyOrgPersonVoList =  findByPage(pageNum, threadSize);
                        map.put(pageNum, partyOrgPersonVoList);
                        return 1;
                    }
                };
                tasks.add(task);
            }
            exec.invokeAll(tasks);
            if (CollUtil.isEmpty(map)) {
                for (int i = 1; i <= threadNum; i++) {
                    List<Article> artList = map.get(i);
                    articles.addAll(artList);
                    map.remove(i);
                }
            }
            exec.shutdown();
            LOGGER.error("【批量查询人员信息】线程任务执行结束,执行任务消耗了 ：" + (System.currentTimeMillis() - start) + "毫秒");
        } catch (Exception e){
            LOGGER.error(e.getMessage());
        }
        return articles;
    }

    public List<Article> findByPage(Integer pageNo, Integer pageSize) {
        Integer offset = (pageNo - 1) * pageSize;
        return dao.findByPage(offset, pageSize);
    }
}
