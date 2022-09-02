package com.example.demo12.aop;


import cn.dev33.satoken.util.SaResult;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Component
@Aspect
public class SysAop {
    //切入点签名
    @Pointcut(value = "@annotation(com.example.demo12.annotation.SysAnnotation))")
    public void log(){
        System.out.println("pointCut签名。。。");
    }

    @Before("log()")
    public void deBefore(JoinPoint joinPoint) throws Throwable{
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        System.out.println("URL : " + request.getRequestURL().toString());
        System.out.println("HTTP_METHOD : " + request.getMethod());
        System.out.println("CLASS_METHOD : " + joinPoint);
        System.out.println("ARGS : " + Arrays.toString(joinPoint.getArgs()));
    }
    //返回通知
    @AfterReturning(returning = "ret",pointcut = "log()")
    public void doAfterReturning(Object ret) throws Throwable{
        // 处理完请求，返回内容
        SaResult saResult = SaResult.data(ret);
        System.out.println("返回通知：方法的返回值 : " + saResult);
    }
    //异常通知
    @AfterThrowing(pointcut = "log()",throwing = "exception")
    public void throwss(JoinPoint joinPoint,Exception exception){
        System.out.println("异常通知：方法异常时执行.....");
        System.out.println("产生异常的方法："+joinPoint);
        System.out.println("异常种类："+exception);
    }


    @After("log()")
    public void after(JoinPoint joinPoint){
        System.out.println("后置通知：最后且一定执行.....");
    }
}
