package com.myproject.firstproject.controller.portal;

import com.github.pagehelper.PageInfo;
import com.myproject.firstproject.common.Const;
import com.myproject.firstproject.common.ResponseCode;
import com.myproject.firstproject.common.ResultDataDto;
import com.myproject.firstproject.entity.Shipping;
import com.myproject.firstproject.entity.User;
import com.myproject.firstproject.service.IShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @ClassName ShippingController
 * @Description TODO
 * @Author aisino
 * @Date 2018/12/19 9:42
 * @Version 1.0
 **/
@RestController
@RequestMapping("/shipping")
public class ShippingController {

    @Autowired
    private IShippingService shippingService;

    @RequestMapping("/add")
    public ResultDataDto add(HttpSession session, Shipping shipping) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ResultDataDto.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        return shippingService.add(user.getId(), shipping);
    }

    @RequestMapping("/del")
    public ResultDataDto del(HttpSession session, Integer shippingId) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ResultDataDto.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        return shippingService.del(user.getId(), shippingId);
    }

    @RequestMapping("/update")
    public ResultDataDto update(HttpSession session, Shipping shipping) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ResultDataDto.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        return shippingService.update(user.getId(), shipping);
    }

    @RequestMapping("/select")
    public ResultDataDto<Shipping> select(HttpSession session, Integer shippingId) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ResultDataDto.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        return shippingService.select(user.getId(), shippingId);
    }

    @RequestMapping("/list")
    public ResultDataDto<PageInfo> list(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                        @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                        HttpSession session) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ResultDataDto.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        return shippingService.list(user.getId(), pageNum, pageSize);
    }

}