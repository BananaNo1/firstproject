package com.myproject.firstproject.common;

/**
 * @ClassName Const
 * @Description TODO
 * @Author aisino
 * @Date 2018/12/18 14:42
 * @Version 1.0
 **/
public class Const {
    public static final String CURRENT_USER = "currentUser";

    public static final String EMAIL = "email";

    public interface Role {
        int ROLE_CUSTOMER = 0;
        int ROLE_ADMIN = 1;
    }

    public interface Cart {
        /**
         * 即购物车选中状态
         */
        int CHECKED = 1;
        /**
         * 购物车中未选中状态
         */
        int UN_CHECKED = 0;

        String LIMIT_NUM_FAIL = "LIMIT_NUM_FAIL";
        String LIMIT_NUM_SUCCESS = "LIMIT_NUM_SUCCESS";
    }
}
