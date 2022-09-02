package com.example.demo12.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
@Data
@ApiModel(value= "UserSignForm",description = "用户注册表单")
public class UserSignForm {

    @NotBlank(message = "邮箱不为空")
    @Email(message = "邮箱不正确")
    @ApiModelProperty(name = "email",required = true,value = "邮箱",example = "18312133217@163.com")
    private String email;

    @NotBlank(message = "密码不为空")
    @Length(min = 6,max = 20,message = "密码长度6-20")
    @ApiModelProperty(name = "password",required = true,value = "密码",example = "qq12345678")
    private String password;

    @NotBlank(message = "重复密码不为空")
    @Length(min = 6,max = 20,message = "密码长度6-20")
    @ApiModelProperty(name = "verifyPassword",required = true,value = "重复密码",example = "qq12345678")
    private String verifyPassword;

    @NotBlank(message = "验证码不为空")
    @Length(min = 6,max = 6,message = "验证码长度6")
    @ApiModelProperty(name = "verifyCode",required = true,value = "邮箱验证码",example = "1234")
    private String verifyCode;

}
