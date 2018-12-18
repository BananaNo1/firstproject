package com.myproject.firstproject.service.impl;

import com.myproject.firstproject.common.Const;
import com.myproject.firstproject.common.ResultDataDto;
import com.myproject.firstproject.entity.User;
import com.myproject.firstproject.mapper.UserMapper;
import com.myproject.firstproject.service.IUserService;
import com.myproject.firstproject.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Override
    public ResultDataDto<String> register(User user) {
        ResultDataDto<String> stringResultDataDto = this.checkValid(user.getUsername(), Const.CURRENT_USER);
        if(!stringResultDataDto.isSuccess()){
            return stringResultDataDto;
        }
        ResultDataDto<String> resultDataDto = this.checkValid(user.getEmail(), Const.EMAIL);
        if(!resultDataDto.isSuccess()){
            return resultDataDto;
        }
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        int insert = userMapper.insert(user);
        if(insert == 0){
            return ResultDataDto.createByErrorMessage("注册失败");
        }
        return ResultDataDto.createBySuccessMessage("注册成功");
    }


    @Override
    public ResultDataDto<String> checkValid(String str, String type) {
        if(StringUtils.isNotBlank(type)){
            if(Const.CURRENT_USER.equals(type)){
                int resultCount = userMapper.checkUsername(str);
                if(resultCount >0){
                    return ResultDataDto.createByErrorMessage("用户名已经存在");
                }
            }
            if(Const.EMAIL.equals(type)){
                int resultCount = userMapper.checkEmail(str);
                if(resultCount >0){
                    return ResultDataDto.createByErrorMessage("邮箱已经存在");
                }
            }
        }else{
            return ResultDataDto.createByErrorMessage("参数错误");
        }
        return ResultDataDto.createBySuccessMessage("校验成功");
    }




}
