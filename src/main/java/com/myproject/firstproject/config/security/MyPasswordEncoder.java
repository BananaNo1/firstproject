package com.myproject.firstproject.config.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @ClassName MyPasswordEncoder
 * @Description TODO
 * @Author leis
 * @Date 2018/12/17 14:57
 * @Version 1.0
 **/

public class MyPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence charSequence) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.encode(charSequence.toString());
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.matches(s, charSequence.toString());
    }
}
