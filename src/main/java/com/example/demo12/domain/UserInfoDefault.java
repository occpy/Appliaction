package com.example.demo12.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
* 
* @TableName user_info_default
*/

@ApiModel
@Data
public class UserInfoDefault implements Serializable {

    /**
    * 唯一id
    */
    @NotNull(message="[唯一id]不能为空")
    @ApiModelProperty("唯一id")
    private Integer id;
    /**
    * 用户昵称
    */
    @Size(max= 20,message="编码长度不能超过20")
    @ApiModelProperty("用户昵称")
    @Length(max= 20,message="编码长度不能超过20")
    private String nickname;
    /**
    * 用户头像
    */
    @Size(max= 255,message="编码长度不能超过255")
    @ApiModelProperty("用户头像")
    @Length(max= 255,message="编码长度不能超过255")
    private String avatarUrl;
    /**
    * 用户性别
0 - 男
1 - 女
2 - 保密
    */
    @ApiModelProperty("用户性别 0 - 男 1 - 女 2 - 保密")
    private Integer gender;
    /**
    * 修改者
    */
    @ApiModelProperty("修改者")
    private Integer updateBy;
    /**
    * 创建者
    */
    @ApiModelProperty("创建者")
    private Integer createBy;
    /**
    * 创建时间
    */
    @ApiModelProperty("创建时间")
    private Date createTime;
    /**
    * 更新时间
    */
    @ApiModelProperty("更新时间")
    private Date updateTime;
    /**
    * 用户描述(个性签名)
    */
    @Size(max= 255,message="编码长度不能超过255")
    @ApiModelProperty("用户描述(个性签名)")
    @Length(max= 255,message="编码长度不能超过255")
    private String describe;
    /**
    * 常用模板 0 非常用 1常用
    */
    @ApiModelProperty("常用模板 0 非常用 1常用")
    private Integer defaultFlag;

//    /**
//    * 唯一id
//    */
//    private void setId(Integer id){
//    this.id = id;
//    }
//
//    /**
//    * 用户昵称
//    */
//    private void setNickname(String nickname){
//    this.nickname = nickname;
//    }
//
//    /**
//    * 用户头像
//    */
//    private void setAvatarUrl(String avatarUrl){
//    this.avatarUrl = avatarUrl;
//    }
//
//    /**
//    * 用户性别
//0 - 男
//1 - 女
//2 - 保密
//    */
//    private void setGender(Integer gender){
//    this.gender = gender;
//    }
//
//    /**
//    * 修改者
//    */
//    private void setUpdateBy(Integer updateBy){
//    this.updateBy = updateBy;
//    }
//
//    /**
//    * 创建者
//    */
//    private void setCreateBy(Integer createBy){
//    this.createBy = createBy;
//    }
//
//    /**
//    * 创建时间
//    */
//    private void setCreateTime(Date createTime){
//    this.createTime = createTime;
//    }
//
//    /**
//    * 更新时间
//    */
//    private void setUpdateTime(Date updateTime){
//    this.updateTime = updateTime;
//    }
//
//    /**
//    * 用户描述(个性签名)
//    */
//    private void setDescribe(String describe){
//    this.describe = describe;
//    }
//
//    /**
//    * 常用模板 0 非常用 1常用
//    */
//    private void setDefaultFlag(Integer defaultFlag){
//    this.defaultFlag = defaultFlag;
//    }
//
//
//    /**
//    * 唯一id
//    */
//    private Integer getId(){
//    return this.id;
//    }
//
//    /**
//    * 用户昵称
//    */
//    private String getNickname(){
//    return this.nickname;
//    }
//
//    /**
//    * 用户头像
//    */
//    private String getAvatarUrl(){
//    return this.avatarUrl;
//    }
//
//    /**
//    * 用户性别
//0 - 男
//1 - 女
//2 - 保密
//    */
//    private Integer getGender(){
//    return this.gender;
//    }
//
//    /**
//    * 修改者
//    */
//    private Integer getUpdateBy(){
//    return this.updateBy;
//    }
//
//    /**
//    * 创建者
//    */
//    private Integer getCreateBy(){
//    return this.createBy;
//    }
//
//    /**
//    * 创建时间
//    */
//    private Date getCreateTime(){
//    return this.createTime;
//    }
//
//    /**
//    * 更新时间
//    */
//    private Date getUpdateTime(){
//    return this.updateTime;
//    }
//
//    /**
//    * 用户描述(个性签名)
//    */
//    private String getDescribe(){
//    return this.describe;
//    }
//
//    /**
//    * 常用模板 0 非常用 1常用
//    */
//    private Integer getDefaultFlag(){
//    return this.defaultFlag;
//    }



}
