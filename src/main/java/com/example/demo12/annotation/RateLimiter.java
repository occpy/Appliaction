package com.example.demo12.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RateLimiter {

    int NOT_LIMITED = 0;

    /**
     *
     * qps(每秒并发量)
     */
    @AliasFor("qps") double value() default NOT_LIMITED;


    /**
     *
     * qps(每秒并发量)
     */
    @AliasFor("value") double qps() default NOT_LIMITED;


    /**
     * 超时，默认不等待
     */
    int timeout() default 0;

    /**
     * 超时，默认不等待
     */
    TimeUnit timeunit() default TimeUnit.MICROSECONDS;


}
