package com.myproject.firstproject.entity.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName Permission
 * @Description 权限实体类
 * @Author leis
 * @Date 2018/12/17 14:09
 * @Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Permission {

    private Long id;
    private String name;
    private String description;
    private String url;
    private int pid;

}
