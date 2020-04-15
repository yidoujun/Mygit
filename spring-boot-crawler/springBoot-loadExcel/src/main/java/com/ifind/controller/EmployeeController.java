package com.ifind.controller;

import com.alibaba.fastjson.JSONObject;
import com.ifind.entity.Employee;
import com.ifind.service.EmployeeService;
import com.ifind.util.ExcelUtil;
import com.ifind.vo.EmployeeVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller("employeeController")
//@RestController("employeeController")
@RequestMapping("/emp")
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeVO employeeVO;

    @RequestMapping("/index")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("loadExcel");
        return mv;
    }

    /**
     * 下载并解析Excel表格信息
     *
     * @param 导入的excel
     * @param name：文件名
     * @param file：文件路径
     * @return
     */
    Integer flag = 0;
    @ResponseBody
    @RequestMapping("/uploadExcel")
    public String uploadExcel(@RequestParam("file") MultipartFile file, String name, HttpServletRequest request, HttpServletResponse response){

        JSONObject jsonObject = new JSONObject();

        Map<String, Object> map = null;

        try {
            // 判断name是否为空，不为空继续处理
            if (name != null) {
                // 将MultipartFile数据流格式转化为InputStream数据流
                InputStream inputStream = file.getInputStream();
                Workbook wb = null;
                // 判断导入的excel版本是否是2003版本
                if (ExcelUtil.isExcel2003(name)) {
                    wb = new HSSFWorkbook(inputStream);
                }
                // 判断导入的excel版本是否是2007版本
                if (ExcelUtil.isExcel2007(name)) {
                    wb = new XSSFWorkbook(inputStream);
                }
                map = employeeVO.loadExcel(wb, flag);
                Integer allRowNum = (Integer) map.get("allRowNum");
                Integer count = (Integer)map.get("count");
                jsonObject.put("count","共计"+allRowNum+"条数据，导入成功"+count+"条数据，导入失败"+(allRowNum-count)+"条");
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        flag = (Integer) map.get("flag");
        jsonObject.put("code", map.get("code"));
        jsonObject.put("message", map.get("message"));
        return jsonObject.toString();
    }

    @RequestMapping("/flag")
    @ResponseBody
    public String test(HttpServletResponse response) {
        JSONObject jsonObject=new JSONObject();
        if(flag==100) {
            jsonObject.put("code", 1);
        }
        jsonObject.put("flag", flag);
        return jsonObject.toString();
    }

}
