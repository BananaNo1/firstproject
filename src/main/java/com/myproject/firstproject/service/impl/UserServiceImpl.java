package com.myproject.firstproject.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.myproject.firstproject.common.Const;
import com.myproject.firstproject.common.ResultDataDto;
import com.myproject.firstproject.common.TokenCache;
import com.myproject.firstproject.entity.User;
import com.myproject.firstproject.mapper.UserMapper;
import com.myproject.firstproject.service.IUserService;
import com.myproject.firstproject.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

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
    private UserMapper userMapper;

    @Resource
    private ValueOperations<String, String> valueOperations;

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
        if (!stringResultDataDto.isSuccess()) {
            return stringResultDataDto;
        }
        ResultDataDto<String> resultDataDto = this.checkValid(user.getEmail(), Const.EMAIL);
        if (!resultDataDto.isSuccess()) {
            return resultDataDto;
        }
        user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));
        int insert = userMapper.insert(user);
        if (insert == 0) {
            return ResultDataDto.createByErrorMessage("注册失败");
        }
        return ResultDataDto.createBySuccessMessage("注册成功");
    }


    @Override
    public ResultDataDto<String> checkValid(String str, String type) {
        if (StringUtils.isNotBlank(type)) {
            if (Const.CURRENT_USER.equals(type)) {
                int resultCount = userMapper.checkUsername(str);
                if (resultCount > 0) {
                    return ResultDataDto.createByErrorMessage("用户名已经存在");
                }
            }
            if (Const.EMAIL.equals(type)) {
                int resultCount = userMapper.checkEmail(str);
                if (resultCount > 0) {
                    return ResultDataDto.createByErrorMessage("邮箱已经存在");
                }
            }
        } else {
            return ResultDataDto.createByErrorMessage("参数错误");
        }
        return ResultDataDto.createBySuccessMessage("校验成功");
    }

    @Override
    public ResultDataDto selectQuestion(String username) {
        ResultDataDto resultDataDto = this.checkValid(username, Const.CURRENT_USER);
        if (!resultDataDto.isSuccess()) {
            return ResultDataDto.createByErrorMessage("用户不存在");
        }
        String s = userMapper.selectQuestionByUsername(username);
        if (StringUtils.isNotBlank(s)) {
            return ResultDataDto.createBySuccess(s);
        }
        return ResultDataDto.createByErrorMessage("问题为空");
    }

    @Override
    public ResultDataDto<String> checkAnswer(String username, String question, String answer) {
        int count = userMapper.checkAnswer(username, question, answer);
        if (count > 0) {
            String forgetToken = UUID.randomUUID().toString();
            TokenCache.setKey(TokenCache.TOKEN_PREFIX + username, forgetToken);
            return ResultDataDto.createBySuccess(forgetToken);
        }
        return ResultDataDto.createByErrorMessage("问题的答案错误");
    }

    @Override
    public ResultDataDto<String> forgetResetPassword(String username, String passwordNew, String forgetToken) {
        if (StringUtils.isBlank(forgetToken)) {
            return ResultDataDto.createByErrorMessage("没有传递token");
        }
        ResultDataDto<String> resultDataDto = this.checkValid(username, Const.CURRENT_USER);
        if (resultDataDto.isSuccess()) {
            return ResultDataDto.createByErrorMessage("用户不存在");
        }
        String token = TokenCache.getKey(TokenCache.TOKEN_PREFIX + username);
        if (StringUtils.isBlank(token)) {
            return ResultDataDto.createByErrorMessage("token无效或者过期");
        }
        if (StringUtils.equals(forgetToken, token)) {
            String md5Password = MD5Util.MD5EncodeUtf8(passwordNew);
            int i = userMapper.updatePasswordByUsername(username, md5Password);
            if (i > 0) {
                return ResultDataDto.createBySuccess("修改密码成功");
            }
        } else {
            return ResultDataDto.createByErrorMessage("token错误,请重新获取重置密码的token");
        }
        return ResultDataDto.createByErrorMessage("修改密码失败");
    }

    @Override
    public ResultDataDto<String> resetPassword(String passwordOld, String passwordNew, User user) {
        int i = userMapper.checkPassword(MD5Util.MD5EncodeUtf8(passwordOld), user.getId());
        if (i == 0) {
            return ResultDataDto.createByErrorMessage("旧密码错误");
        }
        user.setPassword(MD5Util.MD5EncodeUtf8(passwordNew));
        int i1 = userMapper.updateByPrimaryKeySelective(user);
        if (i1 > 0) {
            return ResultDataDto.createBySuccess("更新密码成功");
        }
        return ResultDataDto.createByErrorMessage("更新密码失败");
    }

    @Override
    public ResultDataDto<User> updateInformation(User user) {
        int resultCount = userMapper.checkEmailByUserId(user.getEmail(), user.getId());
        if (resultCount > 0) {
            return ResultDataDto.createByErrorMessage("email已存在");
        }
        User updateUser = new User();
        updateUser.setId(user.getId());
        updateUser.setEmail(user.getEmail());
        updateUser.setPhone(user.getPhone());
        updateUser.setAnswer(user.getAnswer());
        updateUser.setQuestion(user.getQuestion());
        int updateCount = userMapper.updateByPrimaryKeySelective(updateUser);
        if (updateCount > 1) {
            return ResultDataDto.createBySuccessMessage("更新个人信息成功");
        }
        return ResultDataDto.createByErrorMessage("更新个人信息失败");
    }

    @Override
    public ResultDataDto<User> getInformation(Integer userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        if (user == null) {
            return ResultDataDto.createByErrorMessage("找不到该用户");
        }
        user.setPassword(StringUtils.EMPTY);
        return ResultDataDto.createBySuccess(user);
    }

    @Override
    public ResultDataDto checkAdminRole(User user) {
        if (user != null && user.getRole().intValue() == Const.Role.ROLE_ADMIN) {
            return ResultDataDto.createBySuccess();
        }
        return ResultDataDto.createByError();
    }

    @Override
        public User getByToken(HttpServletResponse response, String token) {
            if (StringUtils.isEmpty(token)) {
                return null;
            }
            String us = valueOperations.get("token");
            User user = JSONObject.parseObject(us, User.class);
            if (user != null) {
                addCookie(response, token, user);
            }
            return null;
        }

        private void addCookie(HttpServletResponse response, String token, User user) {
            valueOperations.set("token", token);
            Cookie cookie = new Cookie("token", token);
            cookie.setMaxAge(60);
            cookie.setPath("/");
            response.addCookie(cookie);
    }


}
