package com.example.demo12.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "UserAP",description = "登录表单")
public class UserAP {
    @ApiModelProperty(value = "账号",required = true,example = "123",notes = "必填")
    private String username;
    @ApiModelProperty(value = "密码",required = true,example = "123",notes = "必填")
    private String password;
    @ApiModelProperty(value = "验证码Key",required = true,example = "123",notes = "用于获取缓存中的验证码")
    private String key;
    @ApiModelProperty(value = "验证码",required = true,example = "123",notes = "验证码")
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
