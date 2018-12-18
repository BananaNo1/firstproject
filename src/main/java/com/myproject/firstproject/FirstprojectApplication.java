package com.myproject.firstproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author leis
 * @Descirption 主启动类
 * @date 2018/12/16 21:50
 */

@SpringBootApplication
public class FirstprojectApplication {
    public static void main(String[] args) {
        SpringApplication.run(FirstprojectApplication.class, args);
    }
}

