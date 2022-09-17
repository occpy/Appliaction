package com.example.demo12.service;


import cn.dev33.satoken.util.SaResult;
import com.example.demo12.vo.UserAP;
import com.example.demo12.vo.UserSignForm;

public interface ISysUserService {
    SaResult login(UserAP userAP);

    SaResult sign(UserSignForm form);

    SaResult preVerification(String email,String code,String verifyCode,String verifyKey);

    SaResult postVerification(String password,String verifyPassword,String verifyKey);
}
