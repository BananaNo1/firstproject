package com.myproject.firstproject.controller.portal;

import com.myproject.firstproject.common.Const;
import com.myproject.firstproject.common.ResponseCode;
import com.myproject.firstproject.common.ResultDataDto;
import com.myproject.firstproject.entity.User;
import com.myproject.firstproject.service.ICartService;
import com.myproject.firstproject.vo.CartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @ClassName CartController
 * @Description TODO
 * @Author leis
 * @Date 2018/12/24 15:02
 * @Version 1.0
 **/
@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private ICartService cartService;

    @RequestMapping("/list")
    public ResultDataDto<CartVo> list(HttpSession session) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ResultDataDto.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        return cartService.list(user.getId());
    }

    @RequestMapping("/add")
    public ResultDataDto<CartVo> add(HttpSession session, Integer count, Integer productId) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ResultDataDto.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        return cartService.add(user.getId(), productId, count);
    }

    @RequestMapping("/update")
    public ResultDataDto<CartVo> update(HttpSession session, Integer count, Integer productId) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ResultDataDto.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        return cartService.update(user.getId(), productId, count);
    }

    @RequestMapping("/deleteProduct")
    public ResultDataDto<CartVo> deleteProduct(HttpSession session, String productIds) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ResultDataDto.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        return cartService.deleteProduct(user.getId(), productIds);
    }

    @RequestMapping("/selectAll")
    @ResponseBody
    public ResultDataDto<CartVo> selectAll(HttpSession session) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ResultDataDto.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        return cartService.selectOrUnSelect(user.getId(), null, Const.Cart.CHECKED);
    }

    @RequestMapping("/unSelectAll")
    public ResultDataDto<CartVo> unSelectAll(HttpSession session) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ResultDataDto.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        return cartService.selectOrUnSelect(user.getId(), null, Const.Cart.UN_CHECKED);
    }

    @RequestMapping("/select")
    public ResultDataDto<CartVo> select(HttpSession session, Integer productId) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ResultDataDto.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        return cartService.selectOrUnSelect(user.getId(), productId, Const.Cart.CHECKED);
    }

    @RequestMapping("/unSelect")
    public ResultDataDto<CartVo> unSelect(HttpSession session, Integer productId) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ResultDataDto.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        return cartService.selectOrUnSelect(user.getId(), productId, Const.Cart.UN_CHECKED);
    }

    @RequestMapping("/getCartProductCount")
    @ResponseBody
    public ResultDataDto<Integer> getCartProductCount(HttpSession session) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ResultDataDto.createBySuccess(0);
        }
        return cartService.getCartProductCount(user.getId());
    }
}
