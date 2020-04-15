package com.ifind.service;

import com.ifind.config.DynamicConfig;
import com.ifind.util.BaseSite;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service("cnkiProcessor")
public class CnkiProcessor implements PageProcessor {

    private Site site = BaseSite.me(3);

    @Override
    public void process(Page page) {
//        testCrawler();
        testCrawler1();
    }

    @Override
    public Site getSite() {
        return site;
    }


    public void testCrawler() {
        ChromeDriverService service = new ChromeDriverService.Builder().usingDriverExecutable(new File(DynamicConfig.WEBDRIVER)).usingAnyFreePort().build();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--lang=zh_CN.UTF-8");
        WebDriver driver = null;
        try {
            service.start();
            driver = new ChromeDriver(service, chromeOptions);
            driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
            // 请求cnki
            driver.get("https://kns.cnki.net/kns/brief/result.aspx?dbprefix=scdb");
            // 填充主题词
            driver.findElement(By.name("txt_1_value1")).clear();
            driver.findElement(By.name("txt_1_value1")).sendKeys("数据挖掘");
            Thread.sleep(2000);
            Actions action = new Actions(driver);
            //点击检索按钮
            driver.findElement(By.xpath("//div[@class='btnPlace2']/input")).click();
            Thread.sleep(2000);
            // 定位iframe
//            WebElement iframe = driver.findElement(By.id("iframeResult"));
            driver.switchTo().frame("iframeResult");
            System.out.println(driver.getPageSource());
            // 存储论文url
            List<String> urls = new ArrayList<String>();

            /*for (int i = 0; i < 10; i++) {
                // 获取窗口
                String now_handle = driver.getWindowHandle();
                Set<String > all_handle = driver.getWindowHandles();
                // 判断窗口是否一致
                for (String handle :all_handle) {
                    if (handle != now_handle) {
                        driver.switchTo().window(handle);
                        driver.switchTo().frame(iframe);
                        System.out.println(driver.getPageSource());
                        // 选择页面显示50个论文的按钮
                        WebElement btn = driver.findElement(By.id("id_grid_display_num"));
                        btn.click();
                        Document jsoup = new Document(driver.getPageSource());
                        System.out.println(jsoup);
                    }
                }
                // 线程休眠
                Thread.sleep(1000);
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(driver != null) {
                driver.close();
                driver.quit();
            }
        }
    }

    public void testCrawler1(){
        ChromeDriverService service = new ChromeDriverService.Builder().usingDriverExecutable(new File(DynamicConfig.WEBDRIVER)).usingAnyFreePort().build();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--lang=zh_CN.UTF-8");
        WebDriver driver = null;
        try {
            service.start();
            driver = new ChromeDriver(service, chromeOptions);
            driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
            // 请求cnki
            driver.get("http://epub.cnki.net/KNS/brief/result.aspx?dbprefix=CMFD");
            //高级搜索
            WebElement high = driver.findElement(By.xpath("//*[@id=\"1_3\"]/a"));
            high.click();
            Thread.sleep(1000);
            // 定位元素
            WebElement in = driver.findElement(By.name("txt_1_value1"));
            // 定义搜索内容
            String searchWord = "数据挖掘";
            // 发生搜索内容
            in.sendKeys(searchWord);
            driver.findElement(By.xpath("//*[@id='ddSubmit']/span")).click();
            driver.findElement(By.xpath("//*[@id='btnSearch']")).click();
            Thread.sleep(2000);
            // 定位iframe
            WebElement iframe = driver.findElement(By.id("iframeResult"));
            driver.switchTo().frame(iframe);
            Thread.sleep(2000);

            // 获取当前页面的句柄
            String now_handle = driver.getWindowHandle();
            System.out.println(now_handle);
            // 获取所有句柄
            Set<String> all_handle = driver.getWindowHandles();
            System.out.println(all_handle);
            //List<String> urls = new ArrayList<String>();
            /*for (int i = 0; i < 10; i++) {
                //获取当前窗口---返回的是一个字符串类型
                String now_handle = driver.getWindowHandle();
                //获取所有窗口
                Set<String> all_handles = driver.getWindowHandles();
                //判断窗口是否一致
                for (String handle : all_handles) {
                    if (handle != now_handle) {
                        // 切换窗口，进入指定窗口
                        driver.switchTo().window(handle);
                        // 切换iframe，进入指定的iframe
                        driver.switchTo().frame(iframe);
                        //选择50页
                        WebElement btn = ((ChromeDriver) driver).findElementByXPath("//*[@id='id_grid_display_num']/a[3]");
                        btn.click();
                        //获取页面内容
                        String content = driver.getPageSource();
                        Document doc = Jsoup.parse(content);
                        //System.out.println(doc);
                        Elements elements = doc.select("#GridTableContent tbody tr").removeAttr("class");
                        System.out.println("============================");
                        for (Element e: elements) {
                            System.out.println("======进入for=======");
                           String  tempUrl = e.select("tr td a#fz14").attr("href");
                            System.out.println(tempUrl);
                        }
                        //获取iframe元素内容直至tr
                        List<WebElement> tb = driver.findElements(By.xpath("//*[@id=\"ctl00\"]/table/tbody/tr[2]"));
                        for (WebElement t : tb) {
                            List<WebElement> tbod = t.findElements(By.tagName("tbody"));
                            for (WebElement tr : tbod) {
                                List<WebElement> td = tr.findElements(By.tagName("tr"));
                                td.remove(0);
                                for (WebElement tds : td) {
                                    List<WebElement> tdss = tds.findElements(By.tagName("td"));
                                    String url = tdss.get(1).;
                                    String title = tdss.get(1).getText();
                                    String author = tdss.get(2).getText();
                                    String college = tdss.get(3).getText();
                                    String year = tdss.get(4).getText();
                                    System.out.println(title + "--" + author + "--" + college + "--" + year);
                                }
                            }
                        }
                    }
                }
                Thread.sleep(2000);
                *//*WebElement nextPage = driver.findElement(By.xpath("//*[@id='Page_next']"));
                nextPage.click();*//*
            }*/
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 测试浏览器开启
     */
/*    public void testChrome() {
        System.setProperty("webdriver.chrome.driver", DynamicConfig.WEBDRIVER);
        WebDriver driver = new ChromeDriver();
        System.out.println(driver);
    }*/
}
