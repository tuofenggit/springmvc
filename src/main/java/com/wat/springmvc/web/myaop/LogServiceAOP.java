package com.wat.springmvc.web.myaop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

/**
 * AOP Log
 */
@Aspect
@Component
public class LogServiceAOP {


    @Pointcut("@annotation(com.wat.springmvc.web.annotation.MyAnnotation)")
    public void point() {
    }


    //@Before("point()")
    public void methodCachePointcut() {
        System.err.println("开启事物11111111111111111111111");
    }

    /**
     * aop:after-returning 方法执行成功后调用
     */
    //@AfterReturning("point()")
    public void afterReturning() {
        System.err.println("提交事物");

    }

    /**
     * 相当于 finally 方法
     */
    public void afterMethod() {
        System.err.println("无论是否执行成功都将调用！");
    }

    /**
     * 参数名称必须和 spring配置aop throwing 中一致
     *
     * @param ex
     */
    public void afterthrowing(Throwable ex) {
        System.err.println("异常信息：" + ex.getMessage());
    }

    /**
     * 必须有一个object 的返回值
     *
     * @param joinPoint
     * @return
     */
    @Around("point()")
    public Object aroundMethod(ProceedingJoinPoint joinPoint) {

        System.out.println("before");

        try {

            Annotation[] par = joinPoint.getClass().getAnnotations();
            Object[] args = joinPoint.getArgs();


            Object object = joinPoint.proceed();

            System.out.println("after returning");
            return object;

        } catch (Throwable e) {
            // TODO Auto-generated catch block
            afterthrowing(e);
        } finally {
            afterMethod();
        }

        return null;

    }


}
