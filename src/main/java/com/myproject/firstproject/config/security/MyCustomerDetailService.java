package com.myproject.firstproject.config.security;

import com.myproject.firstproject.entity.security.UserLoginInfo;
import com.myproject.firstproject.entity.security.Permission;
import com.myproject.firstproject.mapper.security.PermissionMapper;
import com.myproject.firstproject.mapper.security.UserLoginInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName MyCustomerDetailService
 * @Description TODO
 * @Author leis
 * @Date 2018/12/14 19:31
 * @Version 1.0
 **/
@Component
public class MyCustomerDetailService implements UserDetailsService {

    @Autowired
    UserLoginInfoMapper userLoginInfoMapper;

    @Autowired
    PermissionMapper permissionMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserLoginInfo userLoginInfo = userLoginInfoMapper.findByUserName(username);
        if (userLoginInfo != null) {
            List<Permission> permissions = permissionMapper.findByUserId(userLoginInfo.getId());
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            for (Permission permission : permissions) {
                if (permission != null && permission.getName() != null) {
                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission.getName());
                    grantedAuthorities.add(grantedAuthority);
                }
            }
            return new User(userLoginInfo.getUsername(), userLoginInfo.getPwd(), grantedAuthorities);
        } else {
            throw new UsernameNotFoundException("username:   " + username + "do not exist!!!!!!!!");
        }
    }
}
