package com.myproject.firstproject.entity.security;

import lombok.Data;

import java.util.List;

/**
 * @ClassName SysUser
 * @Description TODO
 * @Author aisino
 * @Date 2018/12/17 17:21
 * @Version 1.0
 **/
@Data
public class SysUser {
    private Integer id;
    private String username;
    private String password;

    private List<SysRole> roles;
}
