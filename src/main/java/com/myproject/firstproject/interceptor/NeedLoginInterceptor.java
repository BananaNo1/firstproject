package com.myproject.firstproject.interceptor;

import com.myproject.firstproject.annotation.NeedLogin;
import com.myproject.firstproject.common.ResponseCode;
import com.myproject.firstproject.entity.User;
import com.myproject.firstproject.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @ClassName NeedLoginInterceptor
 * @Description TODO
 * @Author leis
 * @Date 2018/12/28 10:34
 * @Version 1.0
 **/
@Service
public class NeedLoginInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private IUserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            User user = getUser(request, response);
            UserContext.setUser(user);
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            NeedLogin needLogin = handlerMethod.getMethodAnnotation(NeedLogin.class);
            if (needLogin == null) {
                return true;
            }
            boolean login = needLogin.needLogin();
            if (login) {
                if (user == null) {
                    render(response, ResponseCode.ERROR.getDesc());
                    return false;
                }
            }
        }
        return super.preHandle(request, response, handler);
    }

    private void render(HttpServletResponse response, String code) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        OutputStream outputStream = response.getOutputStream();
        outputStream.write(code.getBytes("UTF-8"));
        outputStream.flush();
        outputStream.close();
    }

    private User getUser(HttpServletRequest request, HttpServletResponse response) {
        String parameterToken = request.getParameter("token");
        String cookieToken = getCookieValue(request, "token");
        if (StringUtils.isEmpty(parameterToken) && StringUtils.isEmpty(cookieToken)) {
            return null;
        }
        String token = StringUtils.isEmpty(parameterToken) ? cookieToken : parameterToken;
        return userService.getByToken(response, token);
    }

    private String getCookieValue(HttpServletRequest request, String token) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length < 0) {
            return null;
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(token)) {
                return cookie.getValue();
            }
        }
        return null;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }
}
