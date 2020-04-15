package com.ifind.util;

import com.ifind.entity.Product;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * pdf基础操作工具类
 *
 * @author 易都军
 * @date 2020/3/24 16:39
 */
public class PdfUtil {

    /**
     * 获取段落
     *
     * @param content           内容
     * @param alignment         格式
     * @return
     */
    public static Paragraph getParagraph(String content, int alignment) {
        Paragraph paragraph = new Paragraph();
        if (alignment == Element.ALIGN_CENTER){
            paragraph.setAlignment(alignment);
        } else {
            // 设置首行缩进
            paragraph.setFirstLineIndent(25);
            // 设置左间距
            paragraph.setIndentationLeft(20);
            // 设置右间距
            paragraph.setIndentationRight(20);
            // 段落后留白
            paragraph.setSpacingAfter(30);
        }
        // 设置字体
        paragraph.setFont(getChinaFont(20));
        // 将内容写进段落
        Chunk chunk = new Chunk(content);
        paragraph.add(chunk);
        return paragraph;
    }

    /**
     *  创建表格
     *
     * @param products          需要写入的数据
     * @return
     */
    public static PdfPTable getTable(List<Product> products){
        // 创建表格对象
        PdfPTable table = new PdfPTable(3);

        // 添加标题
        PdfPCell cell4 = new PdfPCell(new Phrase("名字", getChinaFont(12)));
        cell4.setFixedHeight(20f);
        table.addCell(cell4);
        PdfPCell cell5 = new PdfPCell(new Phrase("编号", getChinaFont(12)));
        cell5.setFixedHeight(20f);
        table.addCell(cell5);
        PdfPCell cell6 = new PdfPCell(new Phrase("价格", getChinaFont(12)));
        cell6.setFixedHeight(20f);
        table.addCell(cell6);


        // 添加内容
        for (int i = 0; i < products.size(); i++) {
            PdfPCell cell1 = new PdfPCell(new Phrase(products.get(i).getProductName(), getChinaFont(12)));
            cell1.setFixedHeight(20f);
            table.addCell(cell1);

            PdfPCell cell2 = new PdfPCell(new Phrase(products.get(i).getProductCode(), getChinaFont(12)));
            cell2.setFixedHeight(20f);
            table.addCell(cell2);

            PdfPCell cell3 = new PdfPCell(new Phrase(products.get(i).getPrice()+"", getChinaFont(12)));
            cell3.setFixedHeight(20f);
            table.addCell(cell3);
        }
        return table;
    }




    /**
     * 设置中文字体，让其正常显示
     *
     * @param size 字体大小
     * @return
     */
    public static Font getChinaFont(int size) {
        String fontPath = null;
        Font font = null;
        try {
            com.itextpdf.text.pdf.BaseFont bf = null;
            fontPath = ResourceUtils.getURL("classpath:static/simhei.ttf").getPath();
            bf = com.itextpdf.text.pdf.BaseFont.createFont(fontPath, com.itextpdf.text.pdf.BaseFont.IDENTITY_H,
                    BaseFont.NOT_EMBEDDED);
            font = new Font(bf, size);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return font;
    }
}
