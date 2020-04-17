package com.ydj.demo.annotation;

import java.lang.annotation.*;

/**
 * 自定义注解
 * 1、@Target
 * 作用：说明Annotation所修饰的对象范围，用于描述注解的使用范围（即：被描述的注解可以用在什么地方）
 * 参数：TYPE:用于描述类、接口(包括注解类型) 或enum声明
 *       FIELD：用于描述域（包括枚举常量）
 *       METHOD：用于描述方法
 *       PARAMETER：用于描述参数
 *       CONSTRUCTOR：于描述构造器
 *       LOCAL_VARIABLE：用于描述局部变量
 *       ANNOTATION_TYPE：用于描述注解类型
 *       PACKAGE:用于描述包
 *       =====下面两个是Java8新增的修饰类型=====
 *       TYPE_PARAMETER
 *       TYPE_USE
 * </P>
 * 2、@Retention
 * 作用：表示需要在什么级别保存该注释信息，用于描述注解的生命周期（即：被描述的注解在什么范围内有效）
 * 参数: SOURCE：在源文件有效（即源文件保留）
 *       CLASS：在class文件中有效（即class文件保留）
 *       RUNTIME：在运行时有效（即运行时保留）
 * </p>
 * 3、@Documented
 * 作用：一个简单的Annotations标记注解，表示是否将注解信息添加在java文档中。
 * </p>
 * 4、@Inherited
 * 作用：@Inherited阐述了某个被标注的类型是可被继承的。如果一个使用了@Inherited修饰的annotation类型被用于一个class，则这个annotation将被用于该class的子类。
 *
 */
@Inherited
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyAnnotation {


}
