package com.myproject.firstproject.mapper.security;

import com.myproject.firstproject.entity.security.SysUser;
import com.myproject.firstproject.entity.security.UserLoginInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 *@Description TODO
 *@Author leis
 *@Date  2018/12/17 14:29
 **/
@Repository
@Mapper
public interface UserLoginInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserLoginInfo record);

    int insertSelective(UserLoginInfo record);

    UserLoginInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserLoginInfo record);

    int updateByPrimaryKey(UserLoginInfo record);

    SysUser findByUserName(String username);
}