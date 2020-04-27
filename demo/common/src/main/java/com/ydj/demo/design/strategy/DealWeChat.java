package com.ydj.demo.design.strategy;

/**
 * 定义具体策咯----处理微信转发
 *
 * @author yidujun
 * @date 2020/4/27 13:28
 */
public class DealWeChat implements DealStrategy{

    @Override
    public void dealMethod(String option) {
        System.out.println("我是微信转发！");
    }
}
