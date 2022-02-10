package com.wj.springcloud.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author ：actor
 * @date ：Created in 2021/8/11 11:16
 * @description：日志切面
 * @modified By：
 * @version: $
 */
@Aspect
@Component
public class LogAspect {

    @Pointcut("execution(public * com.wj.springcloud.controller1.*.*(..))")
    private void pointcut(){

    }

    @Before("pointcut()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        //获取方法名
        String methodName = joinPoint.getSignature().getName();
        //获取参数列表
        Object[] args = joinPoint.getArgs();
    }


    @AfterReturning(value = "pointcut()",returning = "e")
    public void afterReturning(Exception e){
        StringWriter stringWriter = new StringWriter();
        e.printStackTrace(new PrintWriter(stringWriter));
        System.out.println("后置返回通知"+stringWriter.getBuffer().toString());
    }


    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) {
        //获取参数,和方法名
        joinPoint.getArgs();
        joinPoint.getSignature().getName();
        Object proceed = null;
        try {
            System.out.println("环绕前置通知");
            proceed = joinPoint.proceed();
            System.out.println("环绕后置通知");
        } catch (Throwable throwable) {
            System.out.println("环绕异常通知");
            throwable.printStackTrace();
        } finally {
            System.out.println("环绕返回通知");
        }
        return proceed;
    }


    }
