package com.example.demo12.mapper;

import com.example.demo12.domain.Authority;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author 24185
* @description 针对表【authority(权限表)】的数据库操作Mapper
* @createDate 2022-09-01 09:37:59
* @Entity com.example.demo12.domain.Authority
*/
@Mapper
public interface AuthorityMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Authority record);

    int insertSelective(Authority record);

    Authority selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Authority record);

    int updateByPrimaryKey(Authority record);

    List<Authority> searchAllByIdAuthorities(String role);

}
