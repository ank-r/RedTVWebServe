package com.redtv.redtvwebserve;

import com.redtv.redtvwebserve.utils.RedisUtil;
import com.redtv.redtvwebserve.vo.UserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @ClassName RedisTemplateTest
 * @Description
 * @Author admin
 * @Time 2023/2/15 16:15
 * @Version 1.0
 */
@SpringBootTest
public class RedisTemplateTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    @Test
    public void test1(){
        //
        redisTemplate.opsForValue().set("aaa", "test1");
        UserInfo userInfo = UserInfo.getTest();
        redisTemplate.opsForValue().set("user1", userInfo);
        System.out.println(redisTemplate.opsForValue().get("aaa"));
        System.out.println(redisTemplate.opsForValue().get("user1"));


    }
    @Test
    public void test2(){
        System.out.println(RedisUtil.getCaptchaToken());

    }
}
