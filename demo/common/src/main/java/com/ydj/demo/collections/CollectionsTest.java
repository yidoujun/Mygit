package com.ydj.demo.collections;

import org.junit.Test;

import java.util.Collections;
import java.util.List;

/**
 * 测试Conllections接口
 *
 * @author yidujun
 * @date 2020/4/17 13:49
 */
public class CollectionsTest {

    @Test
    public void CollectionsAllTest() {
//        singletonListDemo();
        isPass();
    }

    /**
     * 用于只有一个元素的优化，减少内存分配，无需分配额外的内存
     */
    public void singletonListDemo() {
        Integer id = 0;
        List<Integer> singleId = Collections.singletonList(id);
        System.out.println(singleId);
    }

    public void isPass(){
        int a = 1;
        int b = 2;
        System.out.println("2232323");
        if(a!=1 || b!=2) {
            System.out.println("1111111");
        }
    }
}
