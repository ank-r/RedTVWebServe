package com.redtv.redtvwebserve.utils;


import com.redtv.redtvwebserve.vo.UserInfo;
/**
 * @ClassName HostHolder
 * @Description 持有用户信息,用于代替session对象
 * @Author admin
 * @Time 2023/2/21 10:00
 * @Version 1.0
 */

public class HostHolder {
    private static ThreadLocal<UserInfo> users = new ThreadLocal<>();

    public static void setUser(UserInfo user){
        users.set(user);
    }

    public static UserInfo getUser(){
        return users.get();
    }

    public static void clear(){
        users.remove();
    }
}

