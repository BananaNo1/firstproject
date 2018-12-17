package com.myproject.firstproject.mapper.security;

import com.myproject.firstproject.entity.security.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName PersissionMapper
 * @Description TODO
 * @Author leis
 * @Date 2018/12/17 14:11
 * @Version 1.0
 **/
@Repository
@Mapper
public interface PermissionMapper {
    /**
     * 查询所有的权限
     * @return
     */
    List<Permission> findAll();

    /**
     * 根据用户id查询权限
     * @param userId 用户id
     * @return
     */
    List<Permission> findByUserId(Long userId);
}
