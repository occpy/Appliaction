package com.example.demo12.mapper;

import com.example.demo12.domain.UserInfoDefault;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 24185
* @description 针对表【user_info_default】的数据库操作Mapper
* @createDate 2022-09-02 16:51:53
* @Entity com.example.demo12.domain.UserInfoDefault
*/
@Mapper
public interface UserInfoDefaultMapper {

    int deleteByPrimaryKey(Long id);

    int insert(UserInfoDefault record);

    int insertSelective(UserInfoDefault record);

    UserInfoDefault selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserInfoDefault record);

    int updateByPrimaryKey(UserInfoDefault record);

    UserInfoDefault selectByDefaultFlog();
}
