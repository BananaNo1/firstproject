package com.myproject.firstproject.config.security;



/**
 * @ClassName MyAccessDecisionManager
 * @Description TODO
 * @Author leis
 * @Date 2018/12/18 9:16
 * @Version 1.0
 **/
/*
@Service
public class MyAccessDecisionManager implements AccessDecisionManager {


    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        if (null == configAttributes || configAttributes.size() < 0) {
            return;
        }
        ConfigAttribute configAttribute;
        String role;
        for (Iterator<ConfigAttribute> iter = configAttributes.iterator(); iter.hasNext(); ) {
            configAttribute = iter.next();
            role = configAttribute.getAttribute();
            for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
                if (role.trim().equals(grantedAuthority.getAuthority())) {
                    return;
                }
            }
        }
        throw  new AccessDeniedException("权限不足");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

}
*/