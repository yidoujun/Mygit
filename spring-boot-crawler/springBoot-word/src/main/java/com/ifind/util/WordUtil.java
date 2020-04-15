package com.ifind.util;

import cn.afterturn.easypoi.word.WordExportUtil;
import cn.afterturn.easypoi.word.entity.MyXWPFDocument;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.util.ResourceUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

@Slf4j
public class WordUtil {

    /**
     * 导出Word
     * <p>第一步：生成替换后的word文件，只支持docx</p>
     * <p>第二步：下载生成的文件</p>
     * <p>第一步：删除生成的临时文件</p>
     * 模板变量中变量格式：{{foo}}
     * @param fileName          文件名
     * @param params            替换的参数
     * @param request
     * @param response
     */
    public static void exportWord(String fileName, Map<String, Object> params, HttpServletRequest request, HttpServletResponse response) {
        Assert.notNull(fileName, "导出文件名不能为空");
        Assert.isTrue(fileName.endsWith(".docx"), "wordf导出请使用docx格式");
        InputStream in = null;
        OutputStream out = null;
        try{
            String userAgent = request.getHeader("user-agent").toLowerCase();
            if (userAgent.contains("msie") || userAgent.contains("like gecko")) {
                fileName = URLEncoder.encode(fileName, "UTF-8");
            } else {
                fileName = new String(fileName.getBytes("utf-8"), "ISO-8859-1");
            }
            // 获取模板文件的url
            URL url = ResourceUtils.getURL("classpath:static/testWord.docx");
            // 打开模块文件的数据流
            in = url.openStream();
            // 根据模板文件数据流生成MyXWPFDocument对象
            MyXWPFDocument doc = new MyXWPFDocument(in);
            /**
             * 解析Word2007版本
             * @param doc       word模板
             * @param params    参数
             */
            WordExportUtil.exportWord07(doc, params);
            // 设置强制下载不打开
            response.setContentType("application/force-download");
            // 设置文件名
            response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);
            // 打开一个管道
            out = response.getOutputStream();
            // 将数据写进管道
            doc.write(out);
            out.flush();
            out.close();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            }catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }

    }

    /**
     * 删除文件
     *
     * @param pathname 待删除文件路径
     * @return
     */
    public static boolean deleteFile (String pathname) {
        boolean result = false;
        File file = new File(pathname);
        if (file.exists()) {
            file.delete();
            result = true;
            System.out.println("文件已经被成功删除");
        }
        return result;
    }
}
