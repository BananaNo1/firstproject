package com.myproject.firstproject.controller.portal;

import com.myproject.firstproject.common.Const;
import com.myproject.firstproject.common.ResultDataDto;
import com.myproject.firstproject.entity.User;
import com.myproject.firstproject.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author leis
 * @Date 2018/12/18 14:37
 * @Version 1.0
 **/
@RestController
@RequestMapping("/user")
@Api(value = "用户模块管理",description = "用户模块管理")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/login")
    @ApiOperation(value = "登录")
    public ResultDataDto<User> login(String username,
                                     String password,
                                     HttpSession session) {
        ResultDataDto<User> resultDataDto = userService.login(username, password);
        if (resultDataDto.isSuccess()) {
            session.setAttribute(Const.CURRENT_USER, resultDataDto.getData());
        }
        return resultDataDto;
    }

    @GetMapping("/logout")
    public ResultDataDto logout(HttpSession session) {
        session.removeAttribute(Const.CURRENT_USER);
        return ResultDataDto.createBySuccess();
    }

    @PostMapping("/register")
    @ApiOperation(value = "注册")
    public ResultDataDto<String> register(User user) {
        return userService.register(user);
    }

    @PostMapping("/checkValid")
    @ApiOperation(value = "用户名是否有效")
    public ResultDataDto<String> checkValid(String str,String type){
        return  userService.checkValid(str,type);
    }

    @PostMapping("/getUserInfo")
    @ApiOperation(value = "获取用户信息")
    public ResultDataDto<User> getUserInfo(HttpSession session){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(user!=null){
            return ResultDataDto.createBySuccess(user);
        }
        return ResultDataDto.createByErrorMessage("用户未登录，无法获取当前用户信息");
    }


}
