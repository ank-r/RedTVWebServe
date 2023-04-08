package com.redtv.redtvwebserve.utils;

import com.redtv.redtvwebserve.exception.UserLoginException;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName JWTUtils
 * @Description  使用jwt 登录验证
 * @Author admin
 * @Time 2023/2/20 16:35
 * @Version 1.0
 */
public class JwtUtils {

    public static long getUserId(HttpServletRequest request) throws UserLoginException {
        String  userId = request.getHeader("Authorization");

        String url = request.getRequestURI();
        System.out.println("request url " + url);
        if (userId == null  || userId==""){
            // throw  new UserLoginException("用户没有登录");

            if (url.startsWith("/get/file/")){
                System.out.println("请求文件");
            }
            // System.out.println("-----用户没有登录");
            return 0L;
        }

        return Long.parseLong( userId);

    }
}
