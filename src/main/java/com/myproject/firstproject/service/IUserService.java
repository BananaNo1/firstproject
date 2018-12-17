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

    ResultDataDto<User> login(String username, String password);
}
