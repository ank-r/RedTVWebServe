package com.redtv.redtvwebserve.cotroller;

import com.redtv.redtvwebserve.service.CaptchaService;
import com.redtv.redtvwebserve.vo.ResponseDetails;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

/**
 * @ClassName CaptchaController
 * @Description    图片验证
 * @Author admin
 * @Time 2023/2/15 9:09
 * @Version 1.0
 */
/**
 * 验证码操作处理类  （可生成数字/字母）
 *
 * @author LBF
 */
@RestController
public class CaptchaController
{


    private final RedisTemplate redisTemplate;

    private final CaptchaService captchaService;

    public CaptchaController(RedisTemplate redisTemplate, CaptchaService captchaService) {
        this.redisTemplate = redisTemplate;
        this.captchaService = captchaService;
    }

    /**
     * 生成验证码方法
     */
    @GetMapping("/captcha/get")
    public ResponseDetails getCode() throws IOException
    {
        Map<String,Object> re = captchaService.getCaptcha();
        if (re == null){
            return  ResponseDetails.error("系统繁忙，请重试");
        }

        return ResponseDetails.ok().put("data", re);
    }
}
