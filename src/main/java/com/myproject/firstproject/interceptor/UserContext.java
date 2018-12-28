package com.myproject.firstproject.interceptor;

import com.myproject.firstproject.entity.User;

/**
 * @ClassName UserContext
 * @Description TODO
 * @Author leis
 * @Date 2018/12/28 10:43
 * @Version 1.0
 **/
public class UserContext {

    private static ThreadLocal<User> userHolder = new ThreadLocal<>();

    public static void setUser(User user) {
        userHolder.set(user);
    }

    public static User getUser() {
        return userHolder.get();
    }

    public static void clear() {
        userHolder.remove();
    }
}
