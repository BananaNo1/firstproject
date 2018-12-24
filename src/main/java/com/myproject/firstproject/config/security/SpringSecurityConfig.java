package com.myproject.firstproject.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @ClassName SpringSecurityConfig
 * @Description springSecurity
 * @Author leis
 * @Date 2018/12/14 18:07
 * @Version 1.0
 **/
/*
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MySuccessHandler mySuccessHandler;

    @Autowired
    private MyCustomerDetailService detailService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).
                withUser("admin").
                password(new BCryptPasswordEncoder().encode("123456")).roles("ADMIN");
        auth.userDetailsService(detailService).passwordEncoder(new MyPasswordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .successHandler(mySuccessHandler)
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .csrf().disable()
                .exceptionHandling()
                .accessDeniedPage("/error1");
       // http.addFilterBefore(, FilterSecurityInterceptor.class);
        //        //  super.configure(http);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**", "/css/**", "/images/**");
    }
}
*/
