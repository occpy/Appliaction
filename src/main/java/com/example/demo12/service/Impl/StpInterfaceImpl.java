package com.example.demo12.service.Impl;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.session.SaSessionCustomUtil;
import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.convert.Convert;
import com.example.demo12.domain.Authority;
import com.example.demo12.domain.Role;
import com.example.demo12.mapper.AuthorityMapper;
import com.example.demo12.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StpInterfaceImpl implements StpInterface {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private AuthorityMapper authorityMapper;
    // 返回一个账号所拥有的权限码集合
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        // 返回一个账号所拥有的权限码集合
        List<String> permissionList = new ArrayList<>();
        getRoleList(loginId,loginType).forEach((String roleId) -> {
            SaSession session = SaSessionCustomUtil.getSessionById("role-" + roleId);
            List<String> list = session.get("Permission_List",()-> authorityMapper.searchAllByIdAuthorities(roleId).stream().map(Authority::getDescribe).toList());
            permissionList.addAll(list);
        });

        return permissionList;
    }
    // 返回一个账号所拥有的角色标识集合
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        SaSession session = StpUtil.getSessionByLoginId(loginId);
        return session.get("Role_List",()-> {
            // 从数据库查询这个账号id拥有的角色列表
            return roleMapper.selectRoleListByUserId(Convert.toLong(loginId)).stream().map(Role::getRole).toList();
        });
    }
}
