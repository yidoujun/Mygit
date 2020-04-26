package com.ydj.demo.annotation;

import org.junit.Test;

/**
 * 注解的基本使用
 */
public class AnnotationTest {

    @Test
    public void testAnnotation(){


    }

    @MyAnnotation(name = "yidujun", age = 30)
    public void simpleMethod() {
        String myName;
    }
}
