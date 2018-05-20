package com.wat.springmvc.web.myaop;

import com.wat.springmvc.web.annotation.MyAnnotation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

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
    public Object aroundMethod(ProceedingJoinPoint joinPoint) throws NoSuchMethodException {
        String tableName = null;
        int num = 0;
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        System.out.println("ip:" + request.getRemoteAddr());

        Object[] args = joinPoint.getArgs();
        System.out.println("拦截到了参数：" + args.toString() + "");

        /**
         * 获取拦截的方法
         */
        Signature sig = joinPoint.getSignature();
        MethodSignature msig = null;
        /**
         *                 throw new IllegalArgumentException("");
         */
        if ((sig instanceof MethodSignature)) {
            msig = (MethodSignature) sig;
            Object target = joinPoint.getTarget();
            Method currentMethod = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
            Annotation[] annotations = currentMethod.getAnnotations();
            for (Annotation annotation : annotations) {
                System.out.println("注解类名称为 ：" + annotation.getClass().getName());

                /**
                 * 如果是我们定义的注解 则处理相应的逻辑
                 */
                if (currentMethod != null && currentMethod.isAnnotationPresent(MyAnnotation.class)) {
                    MyAnnotation myAnnotation = currentMethod.getAnnotation(MyAnnotation.class);
                    tableName = myAnnotation.tableName();
                    num = myAnnotation.num();
                    System.err.println(myAnnotation.tableName());
                }
            }
        }

        try {

            /**
             * 方法返回结果
             */
            Object object = joinPoint.proceed();


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
