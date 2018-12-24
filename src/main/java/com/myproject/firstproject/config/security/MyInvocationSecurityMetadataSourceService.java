package com.myproject.firstproject.config.security;



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
