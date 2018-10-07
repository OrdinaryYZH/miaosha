package com.genericyzh.miaosha.access;


import com.genericyzh.miaosha.user.model.UserInfo;

public class UserContext {

    private static ThreadLocal<UserInfo> userHolder = new ThreadLocal<>();

    public static void setUser(UserInfo user) {
        userHolder.set(user);
    }

    public static UserInfo getUser() {
        return userHolder.get();
    }

    public static final void clearUser() {
        userHolder.remove();
    }


}
