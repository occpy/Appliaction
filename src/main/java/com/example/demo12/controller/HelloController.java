package com.example.demo12.controller;

import cn.dev33.satoken.util.SaResult;
import com.example.demo12.annotation.AopAnnotation;
import com.example.demo12.annotation.RateLimiter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/HELLO")
public class HelloController {


    @GetMapping("/helloAop")
    @RateLimiter(value = 50,timeout = 100,timeunit = TimeUnit.MILLISECONDS)
    public String hello(){
        return "hello aop";
    }


    @GetMapping("/helloError")
    public Object helloError(){
        return 1/0;
    }


    @GetMapping("helloAnnotation")
//标有这个注解的方法会被增强
//    @AopAnnotation(desc = "@Annotation")
    public Object helloAnnotation() {
        return "hello annotation";
    }


    @GetMapping("helloForAgrs")
    public SaResult haveArgs(String aaa){
        return SaResult.ok().setData(aaa);
    }


}
