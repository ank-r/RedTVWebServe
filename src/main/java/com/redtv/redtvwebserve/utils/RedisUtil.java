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

    private static final String PREFIX_USER = "user";
    private static final String PREFIX_UV = "uv";
    private static final String PREFIX_DAU = "dau";



    public static String getCaptchaToken(){
        String captchaName = UUID.randomUUID().toString().replace("-", "");
        return PREFIX+SUFFIX+Captcha+SUFFIX+captchaName;

    }

    // 单日UV
    public static String getUVKey(String date) {
        return PREFIX_UV + SUFFIX + date;
    }

    // 区间UV
    public static String getUVKey(String startDate, String endDate) {
        return PREFIX_UV + SUFFIX + startDate + SUFFIX + endDate;
    }

    // 单日活跃用户
    public static String getDAUKey(String date) {
        return PREFIX_DAU + SUFFIX + date;
    }

    // 区间活跃用户
    public static String getDAUKey(String startDate, String endDate) {
        return PREFIX_DAU + SUFFIX + startDate + SUFFIX + endDate;
    }

}
