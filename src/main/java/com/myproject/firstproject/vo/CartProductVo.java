package com.myproject.firstproject.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @ClassName CartProductVo
 * @Description TODO
 * @Author leis
 * @Date 2018/12/24 15:15
 * @Version 1.0
 **/
@Data
public class CartProductVo {

    private Integer id;
    private Integer userId;
    private Integer productId;
    private Integer quantity;
    private String productName;
    private String productSubtitle;
    private String productMainImage;
    private BigDecimal productPrice;
    private Integer productStatus;
    private BigDecimal productTotalPrice;
    private Integer productStock;
    /**
     *此商品是否勾选
     */
    private Integer productChecked;
    /**
     * 限制数量的一个返回结果
     */
    private String limitQuantity;
}
