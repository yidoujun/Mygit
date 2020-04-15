package com.ifind.util;

/**
 * 工具类
 *
 * @author 易都军
 * @date 2020/3/23 13:48
 */
public class ExcelUtil {

    /**
     * 判断文件类型是不是2003版本
     *
     * @param filePath
     * @return
     */
    public static boolean isExcel2003(String filePath) {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    /**
     * 判断文件类型是不是2007版本
     *
     * @param filePath
     * @return
     */
    public static boolean isExcel2007(String filePath) {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }


}
