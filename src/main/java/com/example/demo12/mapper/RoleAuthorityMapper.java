package com.example.demo12.mapper;

import com.example.demo12.domain.RoleAuthority;

/**
* @author 24185
* @description 针对表【role_authority(角色所属权限表)】的数据库操作Mapper
* @createDate 2022-09-01 09:37:59
* @Entity com.example.demo12.domain.RoleAuthority
*/
public interface RoleAuthorityMapper {

    int deleteByPrimaryKey(Long id);

    int insert(RoleAuthority record);

    int insertSelective(RoleAuthority record);

    RoleAuthority selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RoleAuthority record);

    int updateByPrimaryKey(RoleAuthority record);

}
