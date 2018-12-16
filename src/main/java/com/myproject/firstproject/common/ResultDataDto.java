package com.myproject.firstproject.common;


import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;
/**
 * @Descirption  返回结果
 * @author leis
 * @date 2018/12/16 21:46
 */

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ResultDataDto<T> implements Serializable {

    private int status;
    private String msg;
    private T data;

    public ResultDataDto(int status) {
        this.status = status;
    }

    public ResultDataDto(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public ResultDataDto(int status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public ResultDataDto(int status, T data) {
        this.status = status;
        this.data = data;
    }

    @JsonIgnore
    public boolean isSuccess() {
        return this.status == ResponseCode.SUCCESS.getCode();
    }

    public int getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }

    public static <T> ResultDataDto<T> createBySuccess() {
        return new ResultDataDto<>(ResponseCode.SUCCESS.getCode());
    }

    public static <T> ResultDataDto<T> createBySuccessMessage(String msg) {
        return new ResultDataDto<>(ResponseCode.SUCCESS.getCode(), msg);
    }

    public static <T> ResultDataDto<T> createBySuccess(T data) {
        return new ResultDataDto<>(ResponseCode.SUCCESS.getCode(), data);
    }


    public static <T> ResultDataDto<T> createBySuccess(String msg, T data) {
        return new ResultDataDto<T>(ResponseCode.SUCCESS.getCode(), msg, data);
    }


    public static <T> ResultDataDto<T> createByError() {
        return new ResultDataDto<T>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getDesc());
    }


    public static <T> ResultDataDto<T> createByErrorMessage(String errorMessage) {
        return new ResultDataDto<T>(ResponseCode.ERROR.getCode(), errorMessage);
    }

    public static <T> ResultDataDto<T> createByErrorCodeMessage(int errorCode, String errorMessage) {
        return new ResultDataDto<T>(errorCode, errorMessage);
    }

}
