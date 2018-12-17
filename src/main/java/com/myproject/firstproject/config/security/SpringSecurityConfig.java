package com.myproject.firstproject.config.security;

import com.myproject.firstproject.handler.MySuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @ClassName SpringSecurityConfig
 * @Description springSecurity
 * @Author leis
 * @Date 2018/12/14 18:07
 * @Version 1.0
 **/
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MySuccessHandler mySuccessHandler;

    @Autowired
    private MyCustomerDetailService detailService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       /* auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).
                withUser("admin").
                password(new BCryptPasswordEncoder().encode("123456")).roles("ADMIN");*/
        auth.userDetailsService(detailService).passwordEncoder(new MyPasswordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .successHandler(mySuccessHandler)
                .and()
                .logout()
                .and()
                .csrf().disable();
        //  super.configure(http);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**", "/css/**", "/images/**");
    }
}
