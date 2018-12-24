package com.myproject.firstproject.config.security;


/**
 * @ClassName MyCustomerDetailService
 * @Description TODO
 * @Author leis
 * @Date 2018/12/14 19:31
 * @Version 1.0
 **/
/*
@Service
public class MyCustomerDetailService implements UserDetailsService {

    @Autowired
    UserLoginInfoMapper userLoginInfoMapper;

    @Autowired
    PermissionMapper permissionMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = userLoginInfoMapper.findByUserName(username);
        if (sysUser != null) {
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            */
/*List<Permission> permissions = permissionMapper.findByUserId(userLoginInfo.getId());
            for (Permission permission : permissions) {
                if (permission != null && permission.getName() != null) {
                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission.getName());
                    grantedAuthorities.add(grantedAuthority);
                }
            }*//*

            for(SysRole role :sysUser.getRoles()){
                grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
            }
            return new User(sysUser.getUsername(), sysUser.getPassword(), grantedAuthorities);
        } else {
            throw new UsernameNotFoundException("username:   " + username + "do not exist!!!!!!!!");
        }
    }
}
*/
