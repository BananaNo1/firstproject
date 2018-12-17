package com.myproject.firstproject.config.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @ClassName MyPasswordEncoder
 * @Description 密码加密
 * @Author leis
 * @Date 2018/12/17 14:57
 * @Version 1.0
 **/
@Slf4j
public class MyPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence charSequence) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        log.warn("CharSequence:::  " + charSequence);
        log.warn("bCryptPasswordEncoder.encode(charSequence.toString()):: " + bCryptPasswordEncoder.encode(charSequence.toString()));
        return bCryptPasswordEncoder.encode(charSequence.toString());
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        log.warn("CharSequence::: " + charSequence + "******String::: " + s);
        return bCryptPasswordEncoder.matches(charSequence.toString(), s);
    }
}
