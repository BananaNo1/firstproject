package com.myproject.firstproject.service.impl;

import com.myproject.firstproject.common.ResultDataDto;
import com.myproject.firstproject.entity.User;
import com.myproject.firstproject.mapper.UserMapper;
import com.myproject.firstproject.service.IUserService;
import com.myproject.firstproject.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author aisino
 * @Date 2018/12/17 15:43
 * @Version 1.0
 **/
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public ResultDataDto<User> login(String username, String password) {
        Integer integer = userMapper.checkUsername(username);
        if (integer == null || integer <= 0) {
            return ResultDataDto.createByErrorMessage("用户名不存在");
        }
        String md5Password = MD5Util.MD5EncodeUtf8(password);
        User user = userMapper.selectLogin(username, md5Password);
        if (user == null) {
            return ResultDataDto.createByErrorMessage("密码错误");
        }
        return ResultDataDto.createBySuccess("登录成功", user);
    }
}
