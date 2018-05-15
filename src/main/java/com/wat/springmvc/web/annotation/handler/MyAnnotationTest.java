package com.wat.springmvc.web.annotation.handler;

import com.wat.springmvc.web.annotation.MyAnnotation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyAnnotationTest  {

    private String tableName;

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException {
        Method method = Class.forName("com.wat.springmvc.web.annotation.handler.MyAnnotationTest").getDeclaredMethod("getA");
        // 从Method方法中通过方法getAnnotation获得我们设置的注解
        MyAnnotation oneAnnotation = method.getAnnotation(MyAnnotation.class);

        // 得到注解的俩参数
        System.out.println(oneAnnotation.tableName());

    }


    @MyAnnotation(tableName = "123")
    public static String getA() {
        System.out.println("111");
        return "111";
    }



}
