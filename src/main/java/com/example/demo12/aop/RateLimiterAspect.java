package com.example.demo12.aop;

//import com.google.common.util.concurrent.RateLimiter;

import cn.dev33.satoken.exception.SaTokenException;
import cn.hutool.core.util.StrUtil;
import com.example.demo12.annotation.RateLimiter;
import com.example.demo12.commo.ErrorType;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Aspect
@Component
public class RateLimiterAspect {

    /**
     * 单机缓存
     */
    private static final ConcurrentMap<String, com.google.common.util.concurrent.RateLimiter> RATE_LIMITER_CONCURRENT_MAP = new ConcurrentHashMap<>();

    @Pointcut("@annotation(com.example.demo12.annotation.RateLimiter)")
    public void RateLimiterAop(){
        System.out.println("RateLimiterAop...");
    }

    @Before("RateLimiterAop()")
    public void deBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        System.out.println("URL : " + request.getRequestURL().toString());
        System.out.println("HTTP_METHOD : " + request.getMethod());
        System.out.println("CLASS_METHOD : " + joinPoint);
        System.out.println("ARGS : " + Arrays.toString(joinPoint.getArgs()));
    }

    @Around("RateLimiterAop()")
    public Object pointcut(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        // 通过 AnnotationUtils.findAnnotation 获取 RateLimiter 注解
        RateLimiter rateLimiter = AnnotationUtils.findAnnotation(method, RateLimiter.class);
        System.out.println("// 通过 AnnotationUtils.findAnnotation 获取 RateLimiter 注解");
        if (!Objects.isNull(rateLimiter) && rateLimiter.qps() > RateLimiter.NOT_LIMITED){
            double qps = rateLimiter.qps();
            String key = method.getDeclaringClass().getName() + StrUtil.DOT + method.getName();
            System.out.println(key);
            if (RATE_LIMITER_CONCURRENT_MAP.get(key) == null){
                // 初始化 QPS
                System.out.println("// 初始化 QPS");
                RATE_LIMITER_CONCURRENT_MAP.put(key, com.google.common.util.concurrent.RateLimiter.create(qps));
            }
            // 尝试获取令牌
            if (RATE_LIMITER_CONCURRENT_MAP.get(key) != null && !RATE_LIMITER_CONCURRENT_MAP.get(key).tryAcquire(rateLimiter.timeout(), rateLimiter.timeunit())){
                System.out.println("   // 尝试获取令牌");
                throw new SaTokenException(ErrorType.FAST_TO_REQUEST.getMsg()).setCode(ErrorType.FAST_TO_REQUEST.getCode());
            }
        }
        return  point.proceed();
    }

    //异常通知
    @AfterThrowing(pointcut = "RateLimiterAop()",throwing = "exception")
    public void throwss(JoinPoint joinPoint,Exception exception){
        System.out.println("异常通知：方法异常时执行.....");
        System.out.println("产生异常的方法："+joinPoint);
        System.out.println("异常种类："+exception);
    }

}
