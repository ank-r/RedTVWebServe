package com.redtv.redtvwebserve.service;

import java.util.Map;

/**
 * @ClassName CaptchaService
 * @Description
 * @Author admin
 * @Time 2023/2/15 20:07
 * @Version 1.0
 */
public interface CaptchaService {

    /**
     * 生成验证码
     * @return
     */
     Map<String ,Object> getCaptcha();

    /**
     * 校验验证码
     * @return
     */
     Map<String , Object> checkCaptcha();

}
