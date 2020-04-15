package com.ifind.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestPDF {

    public static void main(String[] args) {
        // 创建文本
        Document document = new Document(PageSize.A4, 20, 20, 20 ,20);
        Chunk chunk = null;
        int size = 20;
        try {
            // 写入文本到文件
            PdfWriter.getInstance(document, new FileOutputStream("D:/test/test.pdf"));
            //打开文本
            document.open();
            /**
             * 定义段落----标题
             */
            Paragraph p1 = new Paragraph();
            // 设置一个段落标题，居中
            p1.setAlignment(Element.ALIGN_CENTER);
           /* p1.setSpacingAfter(15f);
            p1.setSpacingBefore(15f);*/
            chunk = new Chunk("This is test!");
            p1.setFont(getChinaFont(20));
            p1.add(chunk);

            /**
             * 定义段落----主题内容
             */
            Paragraph p2 = new Paragraph();
            // 插入数据到段落中
            for (int i = 0; i < 100; i++) {
                chunk = new Chunk("test!" + i +".");
                // 往段落里面添加信息
                p2.add(chunk);
            }
            // 设置首行缩进
            p2.setFirstLineIndent(25);
            p2.setIndentationLeft(20);
            p2.setIndentationRight(20);
            // 段落后留白
            p2.setSpacingAfter(30);
            // 往文本里面添加段落
            document.add(p1);
            document.add(p2);

            /**
             * 添加表格
             */

            // 设置列数---3列
            PdfPTable table = new PdfPTable(3);
            PdfPCell cell = null;

            cell = new PdfPCell(new Phrase("id" , getChinaFont(size)));//产品编号
            cell.setFixedHeight(size);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("name", getChinaFont(size)));//产品名称
            cell.setFixedHeight(size);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("price", getChinaFont(size)));//产品价格
            cell.setFixedHeight(size);
            table.addCell(cell);
            document.add(table);

            /*cell = new PdfPCell(new Phrase("001" , getChinaFont(size)));//产品编号
            cell.setFixedHeight(size);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("product1", getChinaFont(size)));//产品名称
            cell.setFixedHeight(size);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("11.33", getChinaFont(size)));//产品价格
            cell.setFixedHeight(size);
            table.addCell(cell);*/



            // 往文本里面添加表格
            document.add(table);

            // 关闭文本
            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
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
            BaseFont bf = null;
            fontPath = ResourceUtils.getURL("classpath:static/simhei.ttf").getPath();
            bf = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H,
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
