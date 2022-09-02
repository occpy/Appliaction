package com.example.demo12.commo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorType {


    USER_UNDEFINED_ERROR(4000,"用户不存在"),
    VERIFY_CODE_ERROR(4001,"验证码错误"),
    USER_PASSWORD_ERROR(4002,"密码错误"),
    ACCOUNT_CANT_USE(4003,"该用户已禁用"),
    ACCOUNT_CANCEL_USE(4004,"该用户已注销"),
    EMAIL_ERROR_CODE(4005,"该邮箱有问题"),
    USER_IS_HAVING(4006,"该邮箱已被注册"),
    PASSWORD_UN_SAME(4007,"密码不一致"),


    FAST_TO_REQUEST(5000,"手速太快了，慢点儿吧~");


    /**
     * 错误码
     */
    private int code;

    /**
     * 提示信息
     */
    private String msg;


}
