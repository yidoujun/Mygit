package com.ifind.controller;


import com.ifind.entity.Bill;
import com.ifind.service.BillService;
import com.ifind.util.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@RequestMapping("export")
public class BillController {

    @Autowired
    private BillService billService;


    @RequestMapping("/toExcle")
    public ModelAndView show() {
        ModelAndView mv = new ModelAndView();
        String[] list = {"one", "two", "three", "four", "five"};
        ExcelUtil.createExcle(list, "test", "test");
        List<Bill> bill =  billService.loadBillList();
        mv.addObject("bills", bill);
        mv.setViewName("export");
        return mv;
    }

    /**
     * 导出查询报表
     * @param response
     */
    @RequestMapping("/doExport")
    public void doExport(HttpServletResponse response) {
        String fileName = "票据报表";
        try {
            response.setHeader("Content-type", "application/vnd.ms-excel");
            // 解决导出文件名中文乱码
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + ".xls");
            ExcelUtil.templateExport(response.getOutputStream() , billService.loadBillList());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
