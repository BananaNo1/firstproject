package com.myproject.firstproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName ConfigBean
 * @Description TODO
 * @Author leis
 * @Date 2018/12/17 11:34
 * @Version 1.0
 **/
@Configuration
public class ConfigBean {

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

}
