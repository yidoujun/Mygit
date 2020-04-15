package com.ifind.webmagic;

import com.ifind.util.BaseSite;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * 解析爬取到的页面
 *
 * @author 易都军
 * @date 2020/3/18 14:34
 */
@Service("baiduXuShuProcessor")
public class BaiduXuShuProcessor implements PageProcessor {

    public static final String URI_LIST = "/s\\?wd=%E5%8E%86%E5%8F%B2%E7%A0%94%E7%A9%B6&pn=\\d+.*";

    public static final String URL_POST = "http://xueshu\\.baidu\\.com/usercenter/paper/show\\?paperid=\\w+&site=xueshu_se";

    private Site site = BaseSite.me(3);

    @Override
    public void process(Page page) {
        // 列表页，即一级页面
        if (page.getUrl().regex(URI_LIST).match()) {
            // 保存详情页
            page.addTargetRequests(page.getHtml().xpath("//div[@class='sc_content']/h3[@class='c_font']/a").links().regex(URL_POST).all());
            // 保存列表页
            page.addTargetRequests(page.getHtml().xpath("//p[@id='page']").links().regex(URI_LIST).all());
        } else {   // 文章详情页，即二级页面
            // 得到Jsoup对象
            Document doc = Jsoup.parse(page.getHtml().toString());
            //文章标题
            String articleTitle = doc.select(".main-info h3 a").text();
            System.out.println("文章标题：" + articleTitle);
            // 被收藏数
            String articleLike = doc.select(".like-amount-num").text();
            System.out.println("被收藏数：" + articleLike);
            // 阅读量
            String articleReadNum = doc.select(".label-r p").last().text();
            System.out.println("阅读量：" + articleReadNum);
            // 文章作者
            String articleAuthor = doc.select("div.author_wr p.author_text span a").text();
            System.out.println("文章作者：" + articleAuthor);
            // 被引次数
            String articleReference = doc.select("div.ref_wr p.ref-wr-num a").text();
            System.out.println("被引次数：" + articleReadNum);
            // 发布时间
            String articlePub = doc.select("div.year_wr p.kw_main").text();
            System.out.println("发布时间：" + articlePub);
            System.out.println("===========================================================");
            /*// 文章标题
            page.putField("articleTitle",
                    page.getHtml().xpath("//title/text()").get().split("_")[0]);
            // 被收藏数
            page.putField("articleLike",
                    page.getHtml().xpath("//div[@class='c_content']/div[@class='love_wr']/div[@class='label-l']/a/span[@class='like-amount-num']/text()").get());
            // 阅读量
            page.putField("articleReadNum",
                    page.getHtml().xpath("//*[@id='dtl_l']/div[1]/div[1]/div[1]/div[3]/p[2]/text()").get());
            // 文章作者
            page.putField("articleAuthor",
                    page.getHtml().xpath("//div[@class='c_content']/div[@class='author_wr']/p[@class='author_text']/span/a/text()").get());
            // 被引次数
            page.putField("articleReference",
                    page.getHtml().xpath("//div[@class='c_content']/div[@class='ref_wr']/p[@class='ref-wr-num']/a/text()").get().trim());
            // 发布时间
            page.putField("articlePub",
                    page.getHtml().xpath("//div[@class='c_content']/div[@class='year_wr']/p[@class='kw_main']/text()").get().trim());*/

        }
    }

    @Override
    public Site getSite() {
        return site.addHeader("Accept-Language", "zh-CN,zh;q=0.9");
    }
}
