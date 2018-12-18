package com.myproject.firstproject.config.security;

import com.myproject.firstproject.entity.security.Permission;
import com.myproject.firstproject.mapper.security.PermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @ClassName MyInvocationSecurityMetadataSourceService
 * @Description TODO
 * @Author leis
 * @Date 2018/12/18 11:19
 * @Version 1.0
 **/
/*
@Service
public class MyInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private PermissionMapper permissionMapper;

    private HashMap<String, Collection<ConfigAttribute>> map = null;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        if (map == null) {
            loadResourceDefine();
        }
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        AntPathRequestMatcher matcher;
        String url;
        for (Iterator<String> iter = map.keySet().iterator(); iter.hasNext(); ) {
            url = iter.next();
            matcher = new AntPathRequestMatcher(url);
            if (matcher.matches(request)) {
                return map.get(url);
            }
        }
        return null;
    }

    public void loadResourceDefine() {
        map = new HashMap<>(16);
        Collection<ConfigAttribute> configAttributes;
        ConfigAttribute configAttribute;
        List<Permission> all = permissionMapper.findAll();
        for (Permission permission : all) {
            configAttributes = new ArrayList<>();
            configAttribute = new SecurityConfig(permission.getName());
            configAttributes.add(configAttribute);
            map.put(permission.getUrl(), configAttributes);
        }
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
*/
