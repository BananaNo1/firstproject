package com.myproject.firstproject.service;

import com.github.pagehelper.PageInfo;
import com.myproject.firstproject.common.ResultDataDto;
import com.myproject.firstproject.vo.OrderVo;

import java.util.Map;

/**
 * @ClassName IOrderService
 * @Description TODO
 * @Author leis
 * @Date 2018/12/25 17:12
 * @Version 1.0
 **/
public interface IOrderService {

    ResultDataDto pay(Long orderNo, Integer userId, String path);

    ResultDataDto aliCallback(Map<String, String> params);

    ResultDataDto queryOrderPayStatus(Integer userId, Long orderNo);

    ResultDataDto createOrder(Integer userId, Integer shippingId);

    ResultDataDto<String> cancel(Integer userId, Long orderNo);

    ResultDataDto getOrderCartProduct(Integer userId);

    ResultDataDto<OrderVo> getOrderDetail(Integer userId, Long orderNo);

    ResultDataDto<PageInfo> getOrderList(Integer userId, int pageNum, int pageSize);
}
