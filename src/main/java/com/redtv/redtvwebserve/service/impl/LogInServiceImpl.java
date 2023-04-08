package com.redtv.redtvwebserve.service.impl;

import com.redtv.redtvwebserve.dao.UserDao;
import com.redtv.redtvwebserve.dto.LoginDetails;
import com.redtv.redtvwebserve.entity.UserEntity;
import com.redtv.redtvwebserve.service.LogInService;
import com.redtv.redtvwebserve.vo.UserInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @ClassName UserServiceImpl
 * @Description
 * @Author admin
 * @Time 2023/2/15 21:49
 * @Version 1.0
 */
@Service
public class LogInServiceImpl implements LogInService {

    private final UserDao userDao;
    private final RedisTemplate redisTemplate;

    public LogInServiceImpl(UserDao userDao , RedisTemplate redisTemplate) {
        this.userDao = userDao;
        this.redisTemplate = redisTemplate;
    }



    @Override
    public void userRegister(LoginDetails loginDetails) throws LoginException {

            UserEntity userEntity = new UserEntity();

            //是否有重名
            userEntity.setUsername(loginDetails.getUsername());

            //是否邮箱存在
            userEntity.setMail(loginDetails.getMail());
            userEntity.setPhone(loginDetails.getPhone());
            userEntity.setPassword(loginDetails.getPassword());

            //邀请码判断


            //设置初始化的头像和用户背景图
            userEntity.setAvatarUrl("/api/get/file/2023-02-15/618e08db4ce1438299b60c3e75bb013b.JPG");
            userEntity.setTopImgUrl("/api/get/file/2023-02-15/618e08db4ce1438299b60c3e75bb013b.JPG");
            userEntity.setCreateTime(System.currentTimeMillis());

            userDao.insert(userEntity);


    }

    @Override
    public UserInfo userLogin(LoginDetails loginDetails) throws LoginException {

        String userName = loginDetails.getUsername();
        //
        if (userName == null || userName == "" || userName.length() == 0){
            throw new LoginException("请输入用户名！");
        }

        Map<String,Object> query = new HashMap<>(2);

        query.put("username",userName);

        //返回的是个列表，但是应该只有一个唯一用户
        List<UserEntity> userEntityList =  userDao.selectByMap(query);

        if (userEntityList.size()!=1 || userEntityList.get(0) == null){
            throw new LoginException("请输入正确的用户名");
        }



        UserEntity userEntity = userEntityList.get(0);


        if (!userEntity.getPassword().equals(loginDetails.getPassword())){
            throw new LoginException("请输入正确的用户名密码");
        }


        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(userEntity, userInfo);

        //生成token

        String token = UUID.randomUUID().toString().replace("-", "");

        redisTemplate.opsForValue().set(token, userInfo);

        userInfo.setToken(token);


        return  userInfo;
    }
}
