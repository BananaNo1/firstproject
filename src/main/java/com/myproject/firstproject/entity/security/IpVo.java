package com.myproject.firstproject.entity.security;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName IpVo
 * @Description TODO
 * @Author aisino
 * @Date 2018/12/17 11:41
 * @Version 1.0
 **/
@Data
public class IpVo implements Serializable {

    private Integer code;
    private String country;
    private String region;
    private String city;
    private String isp;
}
