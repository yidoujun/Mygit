package com.ydj.demo.design.strategy;

/**
 * 定义具体策咯----处理新浪转发
 *
 * @author yidujun
 * @date 2020/4/27 13:30
 */
public class DealSina implements DealStrategy {

    @Override
    public void dealMethod(String option) {
        System.out.println("我是新浪转发！");
    }

}
