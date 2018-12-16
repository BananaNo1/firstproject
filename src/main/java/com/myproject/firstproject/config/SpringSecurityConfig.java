package com.myproject.firstproject.config;

import com.myproject.firstproject.handler.MyFailureHandler;
import com.myproject.firstproject.handler.MySuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @ClassName SpringSecurityConfig
 * @Description TODO
 * @Author leis
 * @Date 2018/12/14 18:07
 * @Version 1.0
 **/
//@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MySuccessHandler mySuccessHandler;

    @Autowired
    private MyFailureHandler myFailureHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       // auth.userDetailsService().passwordEncoder(new BCryptPasswordEncoder());
        super.configure(auth);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .successHandler(mySuccessHandler).
                failureHandler(myFailureHandler)
        .and().csrf().disable();
        super.configure(http);
    }


}
