package com.example.demo12.mapper;

import com.example.demo12.domain.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author 24185
* @description 针对表【user_info】的数据库操作Mapper
* @createDate 2022-08-31 20:26:40
* @Entity generator.domain.UserInfo
*/
@Mapper
public interface UserInfoMapper {

    int deleteByPrimaryKey(Long id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

    UserInfo searchAllByUserid(Long userId);
}
