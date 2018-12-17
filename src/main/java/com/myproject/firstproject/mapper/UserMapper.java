package com.myproject.firstproject.mapper;

import com.myproject.firstproject.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {
    /**
     * 通过主键删除
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入数据
     *
     * @param record
     * @return
     */
    int insert(User record);

    /**
     * 选择插入
     *
     * @param record
     * @return
     */
    int insertSelective(User record);

    /**
     * 主键查询
     *
     * @param id
     * @return
     */
    User selectByPrimaryKey(Integer id);

    /**
     * 根据主键选择更新
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * 根据主键更新
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(User record);

    /**
     * 通过用户名和密码登录
     * @param username
     * @param password
     * @return
     */
    User selectLogin(@Param("username") String username,@Param("password") String password);

    /**
     * 查询用户名时候存在
     * @param username
     * @return
     */
    Integer checkUsername(String username);
}