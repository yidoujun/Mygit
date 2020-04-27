package com.ydj.demo.design.strategy;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class StrategyTest {

    private static List<DealContext> algs = new ArrayList<>();

    /**
     * 静态块
     */
    static {
        algs.add(new DealContext("Sina", new DealSina()));
        algs.add(new DealContext("WeChat", new DealWeChat()));
    }

    @Test
    public void test(){
        deal("Sina");
    }

    public void deal(String type) {
        DealStrategy dealStrategy = null;
        for (DealContext deal : algs) {
            if(deal.options(type)) {
                dealStrategy = deal.getDeal();
                break;
            }
        }
        dealStrategy.dealMethod(type);
    }
}
