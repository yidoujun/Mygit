package com.ifind.util;


import com.ifind.entity.Product;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * pdf操作工具类
 *
 * @author 易都军
 * @date 2020/3/24 16:07
 *
 */
@Slf4j
public class PdfConstantsUtil {





    public static void createPDF (Document document, PdfWriter writer, List<Product> products) {
        try {
            // 给文档添加标题
            document.addTitle("sheet of product");
            // 给文档添加作者
            document.addAuthor("scurry");
            // 给文档添加主题
            document.addSubject("product sheet.");
            // 给文档添加关键字
            document.addKeywords("product.");
            // 打开文档
            document.open();
            // 创建表格
            PdfPTable table = createTable(writer, products);
            // 添加表格
            document.add(table);
        } catch (DocumentException e) {
            log.error(e.getMessage(), e);
        } finally {
            if (document != null){
                document.close();
            }
        }

    }

    /**
     *  创建表格
     * @param writer
     * @param products
     * @return
     */
    public static PdfPTable createTable(PdfWriter writer, List<Product> products) {

        // 创建表格
        PdfPTable table = new PdfPTable(products.size());
        // 设置列
        PdfPCell cell = null;
        // 设置字体大小
        int size = 20;
        try {
            // 获取字体格式路径
            String fontPath = ResourceUtils.getURL("classpath:static/simhei.ttf").getPath();
            // 可显示中文
            Font font = new Font(BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED));
            for (int i = 0; i < products.size(); i++) {
                // 产品编号
                cell = new PdfPCell(new Phrase(products.get(i).getProductCode(), font));
                cell.setFixedHeight(size);  //设置列的固定高度
                table.addCell(cell);

                // 产品名称
                cell = new PdfPCell(new Phrase(products.get(i).getProductName(), font));
                cell.setFixedHeight(size);  //设置列的固定高度
                table.addCell(cell);

                // 产品价格
                cell = new PdfPCell(new Phrase(products.get(i).getProductCode(), font));
                cell.setFixedHeight(size);  //设置列的固定高度
                table.addCell(cell);
            }
        } catch (FileNotFoundException e) {
            log.error(e.getMessage(), e);
        } catch (DocumentException e) {
            log.error(e.getMessage(), e);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return table;
    }



}
