package com.example.demo12.mapper;

import com.example.demo12.domain.SysUserInfo;
import com.example.demo12.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author 24185
* @description 针对表【user(用户表)】的数据库操作Mapper
* @createDate 2022-09-01 09:37:59
* @Entity com.example.demo12.domain.User
*/
@Mapper
public interface UserMapper {

    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);


    List<User> selectAllByEmailOrUserOrUser(String username);


    List<SysUserInfo> searchAllById(int id);
}
