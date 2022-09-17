package com.example.demo12.controller;


import cn.dev33.satoken.exception.SaTokenException;
import cn.dev33.satoken.util.SaResult;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.captcha.generator.RandomGenerator;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.extra.mail.MailUtil;
import com.example.demo12.annotation.SysAnnotation;
import com.example.demo12.commo.Constants;
import com.example.demo12.commo.ErrorType;
import com.example.demo12.utils.RedisCache;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@SysAnnotation(name = "系统模块",value = "sys")
@Api(tags = "系统模块")
@RestController
@RequestMapping("sys")
public class SysController {


    @Autowired
    private RedisCache redisCache;

    /**
     * 合成redis key
     * @param code
     * @return
     */
    public String verifyCode (String code){
        return Constants.CAPTCHA_CODE_KEY +
                code;
    }

    /**
     * 生成验证码图片
     * @return
     */
    @ApiOperation(value = "生成验证码图片")
    @GetMapping("/lineCaptcha")
    public SaResult lineCaptcha(){
        //定义图形验证码的长和宽
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100,4,4);
        String s = IdUtil.objectId();
        String code = lineCaptcha.getCode();
        redisCache.setCacheObject(verifyCode(s),code,Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
        Map<String,String> stringMap = MapUtil.builder(new HashMap<String,String>())
                .put("imageBase",lineCaptcha.getImageBase64())
                .put("key", s)
                .build();
        return  SaResult.ok().setData(stringMap);

    }

    /**
     * 发送验证码
     * @param code
     * @param email
     */
    @Async()
    public void sendEmail(String code, String email){
        try{
            MailUtil.send(email, "验证码", "<h1>"+code+"</h1> \n 有效期：2分钟", true);
        } catch (Exception e){
            throw new SaTokenException(ErrorType.EMAIL_ERROR_CODE.getMsg()).setCode(ErrorType.EMAIL_ERROR_CODE.getCode());
        }

    }

    /**
     * 缓存验证验证码
     * @param code
     * @param email
     */
    @Async()
    public void setCodeCache(String code, String email){
        redisCache.setCacheObject(Constants.EMAIL_CODE_KEY+email,code,Constants.CAPTCHA_EXPIRATION,TimeUnit.MINUTES);
    }

    /**
     * 发送邮箱验证码
     * @param mail
     * @return
     */
    @ApiOperation(value = "发送邮箱验证")
    @GetMapping("sendEmailCode")
    @Valid
    public SaResult sendEmailCode( @Email @RequestParam @ApiParam(value = "邮箱",name = "mail",required = true, example = "xx.@exmple.com")  String mail){
        RandomGenerator randomGenerator = new RandomGenerator("0123456789", 4);
        String generate = randomGenerator.generate();
        sendEmail(generate,mail);
        setCodeCache(generate,mail);
        return SaResult.ok();
    }
}
