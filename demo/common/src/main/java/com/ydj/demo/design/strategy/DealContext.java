package com.ydj.demo.design.strategy;

/**
 * 定义上下文，负责使用DealStrategy角色
 *
 * @author yidujun
 * @date 2020/4/27 13:33
 */
public class DealContext {

    private String type;
    private DealStrategy deal;

    public DealContext(String type, DealStrategy deal) {
        this.type = type;
        this.deal = deal;
    }

    public DealStrategy getDeal() {
        return deal;
    }

    public boolean options(String type) {
        return this.type.equals(type);
    }
}
