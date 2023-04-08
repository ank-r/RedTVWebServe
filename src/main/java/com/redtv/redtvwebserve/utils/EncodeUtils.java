package com.redtv.redtvwebserve.utils;

import org.apache.tomcat.util.codec.binary.Base64;

/**
 * @ClassName EncodeUtils
 * @Description    讲一个字节数组转换为 base64编码 的字符串
 * @Author admin
 * @Time 2023/2/15 20:03
 * @Version 1.0
 */
public class EncodeUtils {

    private static final String DEFAULT_URL_ENCODING = "UTF-8";

    /**
     * Base64编码.
     */
    public static String base64Encode(byte[] input) {
        return new String(Base64.encodeBase64(input));
    }

    /**
     * Base64解码.
     */
    public static byte[] base64Decode(String input) {
        return Base64.decodeBase64(input);
    }
}

