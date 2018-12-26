package com.myproject.firstproject.vo;

import lombok.Data;

/**
 * @ClassName ShippingVo
 * @Description TODO
 * @Author leis
 * @Date 2018/12/26 11:28
 * @Version 1.0
 **/
@Data
public class ShippingVo {

    private String receiverName;

    private String receiverPhone;

    private String receiverMobile;

    private String receiverProvince;

    private String receiverCity;

    private String receiverDistrict;

    private String receiverAddress;

    private String receiverZip;
}
