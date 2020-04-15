package com.ifind.controller;

import com.ifind.service.CnkiProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import us.codecraft.webmagic.Spider;

@RestController
@RequestMapping("/cnki")
public class CnkiController {

    @Autowired
    private CnkiProcessor cnkiProcessor;

    @RequestMapping("/crawler")
    public void crawler() {
        System.out.println("======启动爬虫=======");
        Spider.create(cnkiProcessor)
                // 开始爬取页面
                .addUrl("https://kns.cnki.net/kns/brief/result.aspx?dbprefix=scdb")
                .thread(1)
                .run();
    }
}
