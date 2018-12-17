package com.myproject.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName Demo
 * @Description TODO
 * @Author  leis
 * @Date 2018/12/17 9:12
 * @Version 1.0
 **/
@Controller
public class Demo {

    @RequestMapping("/security")
    @ResponseBody
    public String  demo(){
        return "Hello security";
    }
}
