package com.myproject.firstproject.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName CartVo
 * @Description TODO
 * @Author leis
 * @Date 2018/12/24 15:14
 * @Version 1.0
 **/
@Data
public class CartVo {

    private List<CartProductVo> cartProductVoList;
    private BigDecimal cartTotalPrice;
    /**
     * 是否已经都勾选
     */
    private Boolean allChecked;
    private String imageHost;

}
