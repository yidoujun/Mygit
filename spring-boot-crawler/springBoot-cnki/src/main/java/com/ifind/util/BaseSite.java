package com.ifind.util;

import us.codecraft.webmagic.Site;

/**
 * 基础Site
 *
 * @author 易都军
 * @date 2020/3/18 14:12
 */
public class BaseSite {

    public static Site me(){
        return me(1);
    }

    public static Site me (int timeOut) {
        Site site = Site.me();
        site.setSleepTime(TimeWaitUtil.getRandomMillionSeconds());
        site.setCharset("UTF-8");
        // 重试次数
        site.setRetryTimes(1);
        site.setTimeOut(timeOut * 60 * 1000);
        site.setUserAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36");
        site.addHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        site.addHeader("Accept-Encoding", "gzip, deflate, br");
        site.addHeader("Accept-Language", "zh-CN,zh;q=0.9");
        site.addHeader("Connection", "keep-alive");
        site.addHeader("Upgrade-Insecure-Requests", "1");
        return site;
    }

}
