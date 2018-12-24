package com.myproject.firstproject.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.myproject.firstproject.common.ResultDataDto;
import com.myproject.firstproject.entity.Shipping;
import com.myproject.firstproject.mapper.ShippingMapper;
import com.myproject.firstproject.service.IShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName ShippingServiceImpl
 * @Description TODO
 * @Author leis
 * @Date 2018/12/19 9:12
 * @Version 1.0
 **/
@Service
public class ShippingServiceImpl implements IShippingService {

    @Autowired
    ShippingMapper shippingMapper;

    @Override
    public ResultDataDto add(Integer userId, Shipping shipping) {
        shipping.setUserId(userId);
        int count = shippingMapper.insert(shipping);
        if (count > 0) {
            Map result = Maps.newHashMap();
            result.put("shippingId", shipping.getId());
            return ResultDataDto.createBySuccess("新增地址成功", result);
        }
        return ResultDataDto.createByErrorMessage("新增地址失败");
    }

    @Override
    public ResultDataDto<String> del(Integer userId, Integer shippingId) {
        int result = shippingMapper.deleteByShippingIdUserId(shippingId, userId);
        if (result > 0) {
            return ResultDataDto.createBySuccessMessage("删除地址成功");
        }
        return ResultDataDto.createByErrorMessage("删除地址失败");
    }

    @Override
    public ResultDataDto update(Integer userId, Shipping shipping) {
        shipping.setUserId(userId);
        int count = shippingMapper.updateByShipping(shipping);
        if (count > 0) {
            return ResultDataDto.createBySuccess("更新地址成功");
        }
        return ResultDataDto.createByErrorMessage("更新地址失败");
    }

    @Override
    public ResultDataDto<Shipping> select(Integer userId, Integer shippingId) {
        Shipping shipping = shippingMapper.selectByShippingIdUserId(userId, shippingId);
        if (shipping == null) {
            return ResultDataDto.createByErrorMessage("无法查询到该地址");
        }
        return ResultDataDto.createBySuccess("查询成功", shipping);
    }

    @Override
    public ResultDataDto<PageInfo> list(Integer userId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Shipping> shippings = shippingMapper.selectByUserId(userId);
        if (shippings == null) {
            return ResultDataDto.createByErrorMessage("查询失败");
        }
        PageInfo pageInfo = new PageInfo(shippings);
        return ResultDataDto.createBySuccess("查询成功", pageInfo);
    }
}
