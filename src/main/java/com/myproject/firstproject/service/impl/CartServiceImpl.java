package com.myproject.firstproject.service.impl;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.myproject.firstproject.common.Const;
import com.myproject.firstproject.common.ResponseCode;
import com.myproject.firstproject.common.ResultDataDto;
import com.myproject.firstproject.entity.Cart;
import com.myproject.firstproject.entity.Product;
import com.myproject.firstproject.mapper.CartMapper;
import com.myproject.firstproject.mapper.ProductMapper;
import com.myproject.firstproject.service.ICartService;
import com.myproject.firstproject.util.BigDecimalUtil;
import com.myproject.firstproject.util.PropertiesUtil;
import com.myproject.firstproject.vo.CartProductVo;
import com.myproject.firstproject.vo.CartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName CartServiceImpl
 * @Description TODO
 * @Author leis
 * @Date 2018/12/24 15:09
 * @Version 1.0
 **/
@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    CartMapper cartMapper;

    @Autowired
    ProductMapper productMapper;

    @Override
    public ResultDataDto<CartVo> add(Integer userId, Integer productId, Integer count) {
        if (productId == null || count == null) {
            return ResultDataDto.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),
                    ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Cart cart = cartMapper.selectCartByUserIdProductId(userId, productId);
        if (cart == null) {
            Cart cartItem = new Cart();
            cartItem.setQuantity(count);
            cartItem.setUserId(userId);
            cartItem.setProductId(productId);
            cartItem.setChecked(Const.Cart.CHECKED);
            cartMapper.insert(cartItem);
        } else {
            count = cart.getQuantity() + count;
            cart.setQuantity(count);
            cartMapper.updateByPrimaryKeySelective(cart);
        }
        return this.list(userId);
    }

    @Override
    public ResultDataDto<CartVo> update(Integer userId, Integer productId, Integer count) {
        if (productId == null || count == null) {
            return ResultDataDto.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),
                    ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Cart cart = cartMapper.selectCartByUserIdProductId(userId, productId);
        if (cart != null) {
            cart.setQuantity(count);
        }
        cartMapper.updateByPrimaryKey(cart);
        return this.list(userId);
    }

    @Override
    public ResultDataDto<CartVo> deleteProduct(Integer userId, String productIds) {
        List<String> productList = Splitter.on(",").splitToList(productIds);
        if (CollectionUtils.isEmpty(productList)) {
            return ResultDataDto.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),
                    ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        cartMapper.deleteByUserIdProductIds(userId, productList);
        return this.list(userId);
    }

    @Override
    public ResultDataDto<CartVo> list(Integer userId) {
        CartVo cartVo = this.getCartVoLimit(userId);
        return ResultDataDto.createBySuccess(cartVo);
    }

    @Override
    public ResultDataDto<CartVo> selectOrUnSelect(Integer userId, Integer productId, Integer checked) {
        return null;
    }

    @Override
    public ResultDataDto<Integer> getCartProductCount(Integer userId) {
        return null;
    }

    private CartVo getCartVoLimit(Integer userId) {
        CartVo cartVo = new CartVo();
        List<Cart> cartList = cartMapper.selectCartByUserId(userId);
        List<CartProductVo> cartProductVoList = Lists.newArrayList();

        BigDecimal cartTotalPrice = new BigDecimal("0");

        if (CollectionUtils.isEmpty(cartList)) {
            for (Cart cartItem : cartList) {
                CartProductVo cartProductVo = new CartProductVo();
                cartProductVo.setId(cartItem.getId());
                cartProductVo.setUserId(userId);
                cartProductVo.setProductId(cartItem.getProductId());

                Product product = productMapper.selectByPrimaryKey(cartItem.getProductId());

                if (product != null) {
                    cartProductVo.setProductMainImage(product.getMainImage());
                    cartProductVo.setProductName(product.getName());
                    cartProductVo.setProductSubtitle(product.getSubtitle());
                    cartProductVo.setProductStatus(product.getStatus());
                    cartProductVo.setProductPrice(product.getPrice());
                    cartProductVo.setProductStock(product.getStock());
                    //判断库存
                    int buyLimitCount = 0;
                    if (product.getStock() >= cartItem.getQuantity()) {
                        //库存充足的时候
                        buyLimitCount = cartItem.getQuantity();
                        cartProductVo.setLimitQuantity(Const.Cart.LIMIT_NUM_SUCCESS);
                    } else {
                        buyLimitCount = product.getStock();
                        cartProductVo.setLimitQuantity(Const.Cart.LIMIT_NUM_FAIL);
                        //购物车中更新有效库存
                        Cart cartForQuantity = new Cart();
                        cartForQuantity.setId(cartItem.getId());
                        cartForQuantity.setQuantity(buyLimitCount);
                        cartMapper.updateByPrimaryKeySelective(cartForQuantity);
                    }
                    cartProductVo.setQuantity(buyLimitCount);
                    //计算总价
                    cartProductVo.setProductTotalPrice(BigDecimalUtil.mul(product.getPrice().doubleValue(), cartProductVo.getQuantity()));
                    cartProductVo.setProductChecked(cartItem.getChecked());
                }


                if (cartItem.getChecked() == Const.Cart.CHECKED) {
                    //如果已经勾选,增加到整个的购物车总价中
                    cartTotalPrice = BigDecimalUtil.add(cartTotalPrice.doubleValue(), cartProductVo.getProductTotalPrice().doubleValue());
                }
                cartProductVoList.add(cartProductVo);
            }

        }
        cartVo.setCartTotalPrice(cartTotalPrice);
        cartVo.setCartProductVoList(cartProductVoList);
        cartVo.setAllChecked(this.getAllCheckedStatus(userId));
        //todo
        cartVo.setImageHost(PropertiesUtil.getProperty("ftp.server.http.prefix"));
        return null;
    }

    private Boolean getAllCheckedStatus(Integer userId) {
        if (userId == null) {
            return false;
        }
        return cartMapper.selectCartProductCheckedStatusByUserId(userId) == 0;
    }
}
