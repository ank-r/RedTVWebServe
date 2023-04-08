package com.redtv.redtvwebserve.exception;

/**
 * @ClassName UserLoginException
 * @Description   用户登录或者注册时候的一些异常
 * @Author admin
 * @Time 2023/2/15 21:39
 * @Version 1.0
 */
public class UserLoginException extends Exception {

    UserLoginException(){

    }
    public UserLoginException(String msg){
        super(msg);
    }


}
