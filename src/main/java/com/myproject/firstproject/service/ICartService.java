package com.myproject.firstproject.service;

import com.myproject.firstproject.common.ResultDataDto;
import com.myproject.firstproject.vo.CartVo;

/**
 * @ClassName ICartService
 * @Description TODO
 * @Author leis
 * @Date 2018/12/24 15:09
 * @Version 1.0
 **/
public interface ICartService {

    ResultDataDto<CartVo> add(Integer userId, Integer productId, Integer count);
    ResultDataDto<CartVo> update(Integer userId,Integer productId,Integer count);
    ResultDataDto<CartVo> deleteProduct(Integer userId,String productIds);

    ResultDataDto<CartVo> list (Integer userId);
    ResultDataDto<CartVo> selectOrUnSelect (Integer userId,Integer productId,Integer checked);
    ResultDataDto<Integer> getCartProductCount(Integer userId);
}
