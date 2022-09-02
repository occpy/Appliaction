package com.example.demo12.mapper;

import com.example.demo12.domain.UserRole;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 24185
* @description 针对表【user_role(用户所属角色表)】的数据库操作Mapper
* @createDate 2022-09-01 09:37:59
* @Entity com.example.demo12.domain.UserRole
*/
@Mapper
public interface UserRoleMapper {

    int deleteByPrimaryKey(Long id);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);

}
