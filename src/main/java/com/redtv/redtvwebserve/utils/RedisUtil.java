package com.redtv.redtvwebserve.utils;

import java.util.UUID;

/**
 * @ClassName RedisUtil
 * @Description
 * @Author admin
 * @Time 2023/2/15 16:58
 * @Version 1.0
 */
public class RedisUtil {



    private static final String PREFIX = "RED-TV";
    private static final String SUFFIX = ":";
    private static final String Captcha = "CAPTCHA";



    public static String getCaptchaToken(){
        String captchaName = UUID.randomUUID().toString().replace("-", "");
        return PREFIX+SUFFIX+Captcha+SUFFIX+captchaName;

    }

}
