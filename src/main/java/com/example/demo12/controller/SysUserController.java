package com.example.demo12.controller;


import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import cn.hutool.core.map.MapUtil;
import com.example.demo12.annotation.RateLimiter;
import com.example.demo12.annotation.SysAnnotation;
import com.example.demo12.domain.User;
import com.example.demo12.service.ISysUserService;
import com.example.demo12.vo.UserAP;
import com.example.demo12.vo.UserSignForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@Api(tags = "用户系统")
@SysAnnotation(name = "用户系统",value = "sysUser")
@RestController
@RequestMapping("sys-user")
@RateLimiter(value = 50,timeout = 100, timeunit = TimeUnit.MILLISECONDS)
public class SysUserController {

    @Autowired
    private ISysUserService iSysUserService;

    /**
     * 用户登录
     * @param userAP
     * @return
     */
    @ApiOperation("用户登录")
    @PostMapping("doLogin")
    public SaResult doLogin(@RequestBody UserAP userAP){
        return iSysUserService.login(userAP);
    }

    /**
     * 用户注册
     * @param form
     * @return
     */
    @ApiOperation("用户注册")
    @PostMapping("doSign")
    public  SaResult doSign(@ApiParam(name = "form",value = "注册表单") @RequestBody UserSignForm form) {
        return iSysUserService.sign(form);
    }

    /**
     * 用户在线状态
     */
    @ApiOperation("用户在线状态")
    @GetMapping("isLogin")
    public SaResult isLogin(){
        return SaResult.ok().setData(StpUtil.isLogin());
    }

    /**
     * 用户退出登录
     * @return
     */
    @ApiOperation("用户退出")
    @PostMapping("doLogout")
    public SaResult doLogout(){
        StpUtil.logout();
        return SaResult.ok();
    }

    /**
     * 用户信息
     * @return
     */
    @ApiOperation("用户信息")
    @GetMapping("info")
    public SaResult info(){
        Map<String,Object> stringObjectMap = MapUtil.builder(new HashMap<String,Object>())
                .put("info",StpUtil.getExtra("info"))
                .put("Role",StpUtil.getRoleList())
                .put("permission",StpUtil.getPermissionList()).build();
        return SaResult.ok().setData(stringObjectMap);
    }


}
