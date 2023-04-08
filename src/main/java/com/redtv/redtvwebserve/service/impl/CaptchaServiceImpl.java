package com.redtv.redtvwebserve.service.impl;

import com.google.code.kaptcha.Producer;
import com.redtv.redtvwebserve.service.CaptchaService;
import com.redtv.redtvwebserve.utils.EncodeUtils;
import com.redtv.redtvwebserve.utils.RedisUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName CaptchaServiceImpl
 * @Description
 * @Author admin
 * @Time 2023/2/15 20:09
 * @Version 1.0
 */
@Service
public class CaptchaServiceImpl  implements CaptchaService {

    /**Producer接口（DefaultKaptcha为接口的实现类）
     *
     */
    @Resource(name = "captchaBean")
    private Producer captchaProducer;


    private final RedisTemplate redisTemplate;

    public CaptchaServiceImpl(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Map<String, Object> getCaptcha() {
        Map<String , Object> re = new HashMap<>(2);

        // 生成captchaToken，拼接用以作验证码的唯一标识
        String captchaToken = RedisUtil.getCaptchaToken();
        // 设置缓存在Redis中验证码的KEY值
        String verifyKey = captchaToken;

        // 保存生成的验证码
        String capStr = null;
        // 保存验证码正确结果（设置缓存在Redis中验证码的Value值）
        String code = null;
        BufferedImage image = null;

        // 生成字符串验证码
        // 生成验证码与正确结果一致
        capStr = code = captchaProducer.createText();
        // 生成图片
        image = captchaProducer.createImage(capStr);

        // 向Redis中缓存基本对象，为了以后进行校验验证码（验证码KEY、正确结果code、验证码过期时间、时间颗粒度（以某计量为单位））

        redisTemplate.opsForValue().set(captchaToken, code,2, TimeUnit.MINUTES);

        re.put("captchaToken",captchaToken);
        // 图片信息转换成二进制流信息输出
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try
        {
            // 通过ImageIO将图片以jpg格式，以流形式写入内存中
            ImageIO.write(image, "jpg", outputStream);
            byte[]  data = outputStream.toByteArray();
            String base64 = EncodeUtils.base64Encode(data).trim();
            base64 = base64.replaceAll("\n", "").replaceAll("\r", "");
            re.put("img", "data:image/jpg;base64," + base64);
        }
        catch (IOException e)
        {

            return  null;
        }




        return re;
    }

    @Override
    public Map<String, Object> checkCaptcha() {
        return null;
    }
}
