package com.cn.springdemo.aopConfig;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @author weilai
 * @description
 * @since 2019/10/15
 */
//声明需要加入ioc容器
@Component
////声明是一个切面
@Aspect
public class MathicAspect {
    //定义切入点
    @Pointcut("execution(* com.cn.springdemo.aop.*.*(..))")
    private void loggingPoint() {
    }

//    InvocationHandler handler = new InvocationHandler() {
//        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//            //前置通知在这里
//            Object result = null;
//            try {
//                result = method.invoke(args);
//                //返回通知，能拿到返回值
//            } catch (Exception e) {
//                e.printStackTrace();
//                //异常通知
//            }
//            //后置通知，可能没有返回值
//            return result;
//        }
//    };

    //前置通知
    //通知的地方为execution中匹配的方法
//    @Before("execution(* com.cn.springdemo.aop.*.*(..))")
//    @Before("loggingPoint()")
    public void beforeMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        System.out.println(methodName + "method start with args " + args);
        System.out.println("aop Before");
    }

    //后置通知
//    @After("loggingPoint()")
    public void afterMethod(JoinPoint joinPoint) {
        System.out.println("aop After");
    }

    //返回值通知
//    @AfterReturning(pointcut = "loggingPoint()")
    public void afterReturningMethod(JoinPoint joinPoint) {
        System.out.println("aop AfterReturning");
    }

    //异常通知
//    @AfterThrowing(pointcut = "loggingPoint()", throwing = "e")
    public void afterThrowingMethod(JoinPoint joinPoint, Exception e) {
        System.out.println("aop AfterThrowing:" + e);
    }

    //全能型选手，环绕通知
//    @Around("loggingPoint()")
    public Object aroundMethod(ProceedingJoinPoint joinPoint) {
        Object obj = null;
        try {
            //前置通知
            System.out.println("around before");
            //目标方法执行，必须手动执行
            obj = joinPoint.proceed();
            //返回通知
            System.out.println("around afterReturning"+ obj);
        } catch (Throwable throwable) {
            //异常通知
            System.out.println("around throwing"+ throwable);
            throwable.printStackTrace();
        }
        //后置通知
        System.out.println("around after");
        return obj;
    }
}
