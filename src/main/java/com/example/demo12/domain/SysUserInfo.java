package com.example.demo12.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysUserInfo implements Serializable {

    /**
     * 唯一id
     */
    private Integer id;

    /**
     * 用户id
     */
    private Integer userid;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户头像
     */
    private String avatarUrl;

    /**
     * 用户性别
     0 - 男
     1 - 女
     2 - 保密
     */
    private Integer gender;

    /**
     * 用户描述(个性签名)
     */
    private String describe;

    private String role;

    private String auth;

    private static final long serialVersionUID = 1L;
}
