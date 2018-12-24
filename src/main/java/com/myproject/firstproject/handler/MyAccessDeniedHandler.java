package com.myproject.firstproject.handler;



import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @ClassName MyAccessDeniedHandler
 * @Description TODO
 * @Author aisino
 * @Date 2018/12/18 13:58
 * @Version 1.0
 * <p>
 *          http.accessDeniedHandler()
 **/
/*
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        PrintWriter writer = response.getWriter();
        writer.print(accessDeniedException.getMessage() + "******错误");
    }
}
*/
