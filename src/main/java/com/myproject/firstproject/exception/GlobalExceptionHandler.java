package com.myproject.firstproject.exception;

import com.myproject.firstproject.common.ResultDataDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName GlobalExceptionHandler
 * @Description 全局异常处理器
 * @Author leis
 * @Date 2018/12/17 15:28
 * @Version 1.0
 **/
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ModelAttribute
    public void setResponse(HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
    }

    @ExceptionHandler(NullPointerException.class)
    public ResultDataDto nullPointExceptionHandler() {
        return ResultDataDto.createByErrorMessage("缺省参数!");
    }

    /*@ExceptionHandler(AccessDeniedException.class)
    public ResultDataDto accessDeniedException(){
        return ResultDataDto.createByErrorMessage("权限不足");
    }*/

}
