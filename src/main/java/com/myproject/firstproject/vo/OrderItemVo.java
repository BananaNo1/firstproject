package com.myproject.firstproject.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @ClassName OrderItemVo
 * @Description TODO
 * @Author leis
 * @Date 2018/12/25 17:16
 * @Version 1.0
 **/
@Data
public class OrderItemVo {

    private Long orderNo;

    private Integer productId;

    private String productName;
    private String productImage;

    private BigDecimal currentUnitPrice;

    private Integer quantity;

    private BigDecimal totalPrice;

    private String createTime;
}
