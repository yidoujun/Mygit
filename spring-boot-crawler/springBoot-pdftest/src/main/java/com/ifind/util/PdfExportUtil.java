package com.ifind.util;

import com.ifind.entity.Product;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * pdf导出工具类
 *
 * @author 易都军
 *
 */
public class PdfExportUtil {

    /**
     * 导出pdf文件
     *
     * @param fileName      文件名
     * @param products      需要导入的参数
     * @param request
     * @param response
     */
    public static void exportPdf(String fileName, List<Product> products, HttpServletRequest request, HttpServletResponse response) {
        Document doc = null;
        OutputStream out = null;
        try {
            // os是一个暂存数据流的管道
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            // 实例化文档对象
            doc = new Document(PageSize.A4, 20, 20 ,20, 20);
            // 创建Pdf
            PdfWriter writer = PdfWriter.getInstance(doc, os);
            // 打开文件
            doc.open();

            // 添加段落信息
            doc.add(PdfUtil.getParagraph("This is Test!", 1));
            doc.add(PdfUtil.getParagraph("xxxxxxxxxxxxxxxxxxxx中文xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx", 0));

            // 添加表格信息
            doc.add(PdfUtil.getTable(products));
            // 关闭文档
            doc.close();

            // 设置一些网络参数
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            response.setContentType("application/pdf");
            fileName += ".pdf";
            //下载文件到本地
            response.setHeader("Content-Disposition","attachment;filename=" + new String(fileName.getBytes(), "iso8859-1"));

            out = response.getOutputStream();
            os.writeTo(out);
            out.flush();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
