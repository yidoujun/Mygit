package com.ifind.webmagic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import us.codecraft.webmagic.Spider;


@RestController
@RequestMapping("/to")
public class BaiduXuShuTask {

    @Autowired
    private BaiduXuShuProcessor baiduXuShuProcessor;

    @Autowired
    private BaiduXuShuPipeline baiduXuShuPipeline;

    @RequestMapping("/crawl")
    public void crawl() {
        System.out.println("======启动爬虫=======");
        Spider.create(baiduXuShuProcessor)
                // 开始爬取页面
                .addUrl("http://xueshu.baidu.com/s?wd=%E5%8E%86%E5%8F%B2%E7%A0%94%E7%A9%B6&pn=0")
                .addPipeline(baiduXuShuPipeline)
                .thread(5)
                .run();
    }

}
