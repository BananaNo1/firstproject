package com.myproject.firstproject.controller.portal;

import com.myproject.firstproject.common.Const;
import com.myproject.firstproject.common.ResultDataDto;
import com.myproject.firstproject.entity.User;
import com.myproject.firstproject.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/login")
    public ResultDataDto<User> login(String username, String password, HttpSession session){
        ResultDataDto<User> resultDataDto = userService.login(username, password);
        if(resultDataDto.isSuccess()){
            session.setAttribute(Const.CURRENT_USER,resultDataDto.getData());
        }
        return resultDataDto;
    }

}
