package com.example.demo12.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@ApiModel(value = "UserAP",description = "登录表单")
public class UserAP {
    @ApiModelProperty(value = "账号",required = true,example = "123",notes = "必填")
    @NotBlank(message = "账号或邮箱不能为空")
    private String username;
    @ApiModelProperty(value = "密码",required = true,example = "123",notes = "必填")
    @NotBlank(message = "密码不能为空")
    private String password;
    @ApiModelProperty(value = "验证码Key",required = true,example = "123",notes = "用于获取缓存中的验证码")
    private String key;
    @ApiModelProperty(value = "验证码",required = true,example = "123",notes = "验证码")
    @NotBlank(message = "验证码不为空")
    @Length(min = 4,max = 4,message = "验证码长度4")
    private String code;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "UserAP{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", key='" + key + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
