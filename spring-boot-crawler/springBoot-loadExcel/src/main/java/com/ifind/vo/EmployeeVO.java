package com.ifind.vo;

import com.ifind.entity.Employee;
import com.ifind.service.EmployeeService;
import org.apache.commons.collections4.map.HashedMap;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("employeeVO")
public class EmployeeVO {

    @Autowired
    private EmployeeService employeeService;

    /**
     * 将Excel数据入库
     * @param wb
     * @param flag
     * @return
     */
    public Map<String, Object> loadExcel(Workbook wb, int flag) {
        /**
         * 定义相关变量
         */
        int count = 0;
        int code = 0;
        StringJoiner buffer = new StringJoiner("\n");
        Map<String, Object> map = new HashedMap<String, Object>();
//        int sheetsNumber = wb.getNumberOfSheets();
        // 获取第一个Sheet
        Sheet sheet = wb.getSheetAt(0);
        // 获取行数
        int allRowNum = sheet.getLastRowNum();
        if(allRowNum==0) {
            flag = 100;//flag是进度条的值
            buffer.add("导入文件数据为空");
        }
        for (int i = 1; i <= allRowNum; i++) {
            if(flag < 100) {
                flag = flag + (100 / i);
            } else {
                flag = 100;
            }
            Row row = sheet.getRow(i);
            if (row != null) {
                Employee emp = new Employee();
                Cell cell1 = row.getCell(0);          // 获取第1个单元格的数据
                Cell cell2 = row.getCell(1);
                Cell cell3 = row.getCell(2);
                Cell cell4 = row.getCell(3);
                /**
                 *  姓名验证
                 */
                if (cell1 != null) {
                    cell1.setCellType(CellType.STRING);
                    emp.setEmpName(cell1.getStringCellValue());
                }else {
                    buffer.add("第" + i + "行的第1列的数据不能为空");
                }
                /**
                 * 手机号码列验证
                 */
                if (cell2 != null) {
                    cell2.setCellType(CellType.STRING);
                    String verify = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
                    String moblie=cell2.getStringCellValue();
                    if (moblie.length() != 11) {
                        buffer.add("第"+i+"行的第1列的手机号码不复合要求11位");
                    } else {
                        // 正则校验
                        Pattern p = Pattern.compile(verify);
                        Matcher m = p.matcher(moblie);
                        boolean isMatch = m.matches();
                        if (isMatch) {
                            // 数据库校验，判断唯一性
                            String phone = cell2.getStringCellValue();
                            Employee empByMoblie = employeeService.getEmpByMoblie(phone);
                            if (empByMoblie == null) {
                                emp.setEmpPhone(cell2.getStringCellValue());
                            } else {
                                buffer.add("第"+i+"行的第2列的手机号码已存在");
                            }
                        } else {
                            buffer.add("第"+i+"行的第2列的手机号码格式错误");
                        }
                    }
                } else {
                    buffer.add("第"+i+"行的第2列的数据不能为空");
                }
                /**
                 * 职位列验证
                 */
                if (cell3 != null) {
                    cell3.setCellType(CellType.STRING);
                    emp.setEmpPosition(cell3.getStringCellValue());
                } else {
                    buffer.add("第"+i+"行的第3列的数据不能为空");
                }
                /**
                 * 部门列验证
                 */
                if (cell4 != null) {
                    cell4.setCellType(CellType.STRING);
                    emp.setEmpDepartment(cell4.getStringCellValue());
                } else {
                    buffer.add("第"+i+"行的第4列的数据不能为空");
                }
                if (emp.getEmpName() != null && emp.getEmpPhone() != null && emp.getEmpPosition() != null && emp.getEmpDepartment() != null) {
                    count ++;
                    // 保存到数据库
                    employeeService.addEmployee(emp);
                }
            }
        }
        code = 1;
        map.put("allRowNum", allRowNum);
        map.put("count", count);
        map.put("code", code);
        map.put("message", buffer.toString());
        map.put("flag", flag);
        return map;
    }

}
