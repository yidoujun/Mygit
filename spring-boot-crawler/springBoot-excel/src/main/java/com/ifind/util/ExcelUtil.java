package com.ifind.util;

import com.ifind.entity.Bill;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * Excel生成/写入工具类
 *
 * @author 易都军
 * @date 2020/3/20 11:08
 */
public class ExcelUtil {

    /**
     * 创建excel模板
     * @param strArrary
     */
    public static void createExcle(String[] strArrary, String sheetName, String fileName) {
        /**
         * 创建一个webbook，对应一个Excel文件
         */
        HSSFWorkbook wb = new HSSFWorkbook();
        /**
         * 在webbook中添加一个sheet，对应Excel文件中的sheet
         */
        HSSFSheet sheet = wb.createSheet(sheetName);
        sheet.setDefaultColumnWidth(20);    //默认列宽
        /**
         * 在sheet中添加表头第0行
         */
        HSSFRow headRow = sheet.createRow(0);
        /**
         * 创建单元格，并设置表头样式
         */
        HSSFCellStyle style = wb.createCellStyle();
        //水平居中
        style.setAlignment(HorizontalAlignment.CENTER);
        //垂直居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFillForegroundColor(IndexedColors.YELLOW.index);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        // 创建列
        HSSFCell cell = null;
        for (int i = 0; i < strArrary.length; i++) {
            cell = headRow.createCell((short) i);
            cell.setCellValue(strArrary[i]);
            cell.setCellStyle(style);
        }

        try {
            // 指向保存的地址
//            String  file = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "static/excel/" + fileName + ".xls").getPath();
            String file = "D:/Daily/" + fileName + ".xls";
            FileOutputStream fout = new FileOutputStream(file);
            wb.write(fout);
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 将生成的Excel写入到输出流里面
     * @param out
     * @param list
     */
    public static void getExport(OutputStream out, List<Bill> list, String sheetName) {
        // 1.创建Excel工作簿对象
        HSSFWorkbook wb = new HSSFWorkbook();
        // 2.创建Excel工作表对象
        HSSFSheet sheet = wb.createSheet(sheetName);
        // 3.创建单元格
        CellStyle cellStyle = wb.createCellStyle();
        // 4.设置单元格的样式
        cellStyle.setFillForegroundColor(IndexedColors.SKY_BLUE.index);

        HSSFRow row = null;
        Bill bill = null;

        for (int i = 0; i < list.size(); i++) {
            bill = list.get(i);
            // 5.创建单元格的行
            row = sheet.createRow(i);
            // 6.设置单元格属性值和样式
            row.createCell(0).setCellStyle(cellStyle);
            row.createCell(0).setCellValue(bill.getBillNo());
            row.createCell(1).setCellStyle(cellStyle);
            row.createCell(1).setCellValue(bill.getBank());
            row.createCell(2).setCellStyle(cellStyle);
            row.createCell(2).setCellValue(bill.getWightedAverageYield().toString());
            row.createCell(3).setCellStyle(cellStyle);
            row.createCell(3).setCellValue(bill.getFaceBillAmt().toString());
            row.createCell(4).setCellStyle(cellStyle);
            row.createCell(4).setCellValue(bill.getRepairDate());
            // 7.设置sheet名称和单元格内容
            wb.setSheetName(0, sheetName);

            try {
                // 8.将Excel写入到输出流里面
                wb.write(out);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 根据Excel模板来导出Excel数据
     * @param out
     * @param list
     * @throws IOException
     */
    public static void templateExport(OutputStream out, List<Bill> list) throws IOException {
        // 1.读取Excel模板
//        File file = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "static/excel/templates.xlsx");
        URL url = ResourceUtils.getURL("classpath:static/excel/templates.xlsx");
        InputStream in = url.openStream();
//        InputStream in = new FileInputStream(file);
        XSSFWorkbook wb = new XSSFWorkbook(in);
        // 2.读取模板里面的所有Sheet
        XSSFSheet sheet = wb.getSheetAt(0);
        // 3.设置公式自动读取
        sheet.setForceFormulaRecalculation(true);
        // 4.向相应的单元格里面设置值
        XSSFRow row = null;   // 对应行
        Bill bill = null;
        for (int i = 0; i < list.size(); i++) {
            row = sheet.createRow(i+2);
            bill = list.get(i);
            // 6.设置单元格属性和样式
            row.createCell(0).setCellValue(bill.getBillNo());
            row.createCell(1).setCellValue(bill.getBank());
            row.createCell(2).setCellValue(bill.getFaceBillAmt().setScale(2, BigDecimal.ROUND_HALF_UP)+"");
            row.createCell(3).setCellValue(bill.getWightedAverageYield().multiply(new BigDecimal(100))+"%");
            row.createCell(4).setCellValue(bill.getRepairDate());
        }
        // 7.设置sheet名称和单元格内容
        wb.setSheetName(0, "票据报表");

        try{
            wb.write(out);
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (wb != null) {
                wb.close();
            }
            if(in != null) {
                in.close();
            }
        }
    }

}
