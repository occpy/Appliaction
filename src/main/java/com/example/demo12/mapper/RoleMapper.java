package com.example.demo12.mapper;

import com.example.demo12.domain.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author 24185
* @description 针对表【role(角色表)】的数据库操作Mapper
* @createDate 2022-09-01 09:37:59
* @Entity com.example.demo12.domain.Role
*/
@Mapper
public interface RoleMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
    List<Role> selectRoleListByUserId(Long id);

}
