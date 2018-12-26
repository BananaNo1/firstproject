package com.myproject.firstproject.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName OrderProductVo
 * @Description TODO
 * @Author leis
 * @Date 2018/12/26 14:21
 * @Version 1.0
 **/
@Data
public class OrderProductVo {
    private List<OrderItemVo> orderItemVoList;
    private BigDecimal productTotalPrice;
    private String imageHost;
}
