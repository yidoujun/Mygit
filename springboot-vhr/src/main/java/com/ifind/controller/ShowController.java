package com.ifind.controller;

import com.ifind.entity.Job;
import com.ifind.util.ResponseVO;
import com.ifind.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/api")
@RestController
@Slf4j
public class ShowController {

    @CrossOrigin
    @RequestMapping("/getData")
    @ResponseBody
    public ResponseVO getData(){
        try{
            List<String> names = new ArrayList<String>();
            names.add("衬衫");
            names.add("羊毛衫");
            names.add("雪纺衫");
            names.add("裤子");
            names.add("高跟鞋");
            names.add("袜子");
            List<Integer> values = new ArrayList<Integer>();
            values.add(5);
            values.add(20);
            values.add(36);
            values.add(10);
            values.add(10);
            values.add(20);
            Map<String, List<?>> map = new HashMap<>();
            map.put("names", names);
            map.put("values", values);
            System.out.println("跨域成功");
            ResponseVO test = ResultUtil.success("操作成功", map);
            return ResultUtil.success("操作成功", map);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResultUtil.error("操作失败");
        }
    }

    @CrossOrigin
    @RequestMapping("/getChina")
    @ResponseBody
    public ResponseVO chinaShow() {
        Job job1 = new Job();
        job1.setId(1);
        job1.setProvinceName("湖南");
        job1.setProvinceNum(20);
        Job job2 = new Job();
        job2.setId(2);
        job2.setProvinceName("浙江");
        job2.setProvinceNum(110);
        List<Job> list = new ArrayList<Job>();
        list.add(job1);
        list.add(job2);
        try {
            System.out.println("跨域成功");
            return ResultUtil.success("操作成功", list);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResultUtil.error("操作失败!");
        }
    }
}
