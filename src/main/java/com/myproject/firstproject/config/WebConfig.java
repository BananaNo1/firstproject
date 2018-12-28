package com.myproject.firstproject.config;

import com.myproject.firstproject.interceptor.NeedLoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @ClassName WebConfig
 * @Description TODO
 * @Author leis
 * @Date 2018/12/28 10:30
 * @Version 1.0
 **/
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private UserResolver userResolver;

    @Autowired
    private NeedLoginInterceptor needLoginInterceptor;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(userResolver);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(needLoginInterceptor);
    }
}
