package com.myproject.firstproject.config.security;



/**
 * @ClassName MyFilterSecurityInterceptor
 * @Description TODO
 * @Author aisino
 * @Date 2018/12/18 10:57
 * @Version 1.0
 **/
/*
@Service
public class MyFilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {


    @Autowired
    private MyInvocationSecurityMetadataSourceService myInvocationSecurityMetadataSourceService;

    @Autowired
    public void setMyAccessDecisionManager(MyAccessDecisionManager myAccessDecisionManager) {
        super.setAccessDecisionManager(myAccessDecisionManager);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        FilterInvocation fi = new FilterInvocation(servletRequest, servletResponse, filterChain);
        this.invoke(fi);
    }


    public void invoke(FilterInvocation fi) throws IOException, ServletException {
        InterceptorStatusToken token = super.beforeInvocation(fi);
        try {
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        } finally {
            super.finallyInvocation(token);
        }
        super.afterInvocation(token, (Object) null);
    }

    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return this.myInvocationSecurityMetadataSourceService;
    }
}
*/
