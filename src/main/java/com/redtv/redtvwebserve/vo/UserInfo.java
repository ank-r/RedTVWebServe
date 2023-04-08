package com.redtv.redtvwebserve.vo;

import com.redtv.redtvwebserve.entity.UserEntity;
import lombok.Data;

/**
 * @ClassName UserInfo
 * @Description
 * @Author admin
 * @Time 2023/2/7 23:07
 * @Version 1.0
 */
@Data
public class UserInfo extends UserEntity {

    /**
     * 用户token
     */
    private String token;

    public static UserInfo getTest(){
        UserInfo userInfo = new UserInfo();
        userInfo.setId(2102L);
        userInfo.setUsername("张三");
        userInfo.setMail("2837246896@qq.com");
        userInfo.setPassword("123456");
        userInfo.setPhone("18263817283");
        userInfo.setCreateTime(System.currentTimeMillis());
        userInfo.setSubmitCount(123L);
        userInfo.setFollowCount(2132L);
        userInfo.setFansCount(2990L);
        userInfo.setIsAdmin(0);
        userInfo.setAvatarUrl("https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png");
        userInfo.setTopImgUrl("https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg");
        userInfo.setIntroduction("用户的简单介绍");

        return userInfo;
    }
}
