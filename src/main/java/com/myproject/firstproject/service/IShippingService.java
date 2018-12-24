package com.myproject.firstproject.service;

import com.github.pagehelper.PageInfo;
import com.myproject.firstproject.common.ResultDataDto;
import com.myproject.firstproject.entity.Shipping;

/**
 * @ClassName IShippingService
 * @Description TODO
 * @Author leis
 * @Date 2018/12/19
 **/
public interface IShippingService {
    ResultDataDto add(Integer userId, Shipping shipping);

    ResultDataDto<String> del(Integer userId, Integer shippingId);

    ResultDataDto update(Integer userId, Shipping shipping);

    ResultDataDto<Shipping> select(Integer userId, Integer shippingId);

    ResultDataDto<PageInfo> list(Integer userId, int pageNum, int pageSize);
}
