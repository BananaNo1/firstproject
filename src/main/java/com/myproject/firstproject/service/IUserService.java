package com.myproject.firstproject.service;

import com.myproject.firstproject.common.ResultDataDto;
import com.myproject.firstproject.entity.User;

/**
 * @ClassName IUserService
 * @Description 用户服务层
 * @Author leis
 * @Date 2018/12/17 15:42
 * @Version 1.0
 **/
public interface IUserService {
    /**
     * 登录接口
     * @param username
     * @param password
     * @return
     */
    ResultDataDto<User> login(String username, String password);

    /**
     * 用户注册
     * @param user
     * @return
     */
    ResultDataDto<String> register(User user);
    /**
     * 检查用户名是否有效
     * @param str
     * @param type
     * @return
     */
    ResultDataDto<String> checkValid(String str,String type);


}
