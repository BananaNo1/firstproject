package com.myproject.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName Demo
 * @Description TODO
 * @Author leis
 * @Date 2018/12/17 9:12
 * @Version 1.0
 **/
//@Controller
public class Demo {

    @RequestMapping("/admin")
    @ResponseBody
    //@PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    public String demo() {
        return "Hello security";
    }

    @RequestMapping("/user")
    @ResponseBody
    public String Access() {
        return "Hello  user!!!!!!!!!!!!";
    }

    @RequestMapping("/error1")
    public String error() {
        return "AccessDenied";
    }


    @RequestMapping("/index")
    public String index() {
        return "index";
    }
}
