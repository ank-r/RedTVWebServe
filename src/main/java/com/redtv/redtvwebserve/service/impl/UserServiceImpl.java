package com.redtv.redtvwebserve.service.impl;

import com.redtv.redtvwebserve.dao.FollowDao;
import com.redtv.redtvwebserve.dao.UserDao;
import com.redtv.redtvwebserve.dto.PasswordDto;
import com.redtv.redtvwebserve.entity.FollowEntity;
import com.redtv.redtvwebserve.entity.UserEntity;
import com.redtv.redtvwebserve.enums.ReturnCodeEnum;
import com.redtv.redtvwebserve.exception.UpdateInfoException;
import com.redtv.redtvwebserve.service.UserService;
import com.redtv.redtvwebserve.utils.HostHolder;
import com.redtv.redtvwebserve.vo.UserInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName UserServiceImpl
 * @Description
 * @Author admin
 * @Time 2023/2/21 10:35
 * @Version 1.0
 */
@Component
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    private final FollowDao followDao;

    public UserServiceImpl(UserDao userDao, FollowDao followDao) {
        this.userDao = userDao;
        this.followDao = followDao;
    }

    @Override
    public UserInfo getUserById(long id) {
        UserEntity userEntity = userDao.selectById(id);
        if (userEntity == null){
            return null;
        }
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(userEntity, userInfo);
        return userInfo;
    }

    @Override
    public List<UserInfo> getUserList() {

        List<UserEntity> userEntities = userDao.selectByMap(null);

        List<UserInfo> userInfos = new ArrayList<>(userEntities.size());

        for (UserEntity entity : userEntities){
            UserInfo userInfo = new UserInfo();
            BeanUtils.copyProperties(entity, userInfo);

            userInfos.add(userInfo);
        }

        return userInfos;
    }

    @Override
    public int updateInfo(UserInfo userInfo) {
        UserEntity userEntity = new UserEntity();

        BeanUtils.copyProperties(userInfo, userEntity);

        int re = userDao.updateById(userEntity);
        if (re<=0){
            return ReturnCodeEnum.SYSTEM_ERROR.getCode();
        }
        return ReturnCodeEnum.SUCCESS.getCode();
    }

    @Override
    public void updatePassword(PasswordDto passwordDto) throws UpdateInfoException {

        UserInfo userInfo = HostHolder.getUser();
        if (!userInfo.getPassword().equals(passwordDto.getOldPassword())){
            throw new RuntimeException("旧密码错误");
        }
        userInfo.setPassword(passwordDto.getNewPassword());
        updateInfo(userInfo);

    }

    @Override
    public List<UserInfo> getFans(long userId) {
        Map<String,Object> map = new HashMap<>();
        map.put("followed_id", userId);
        List<FollowEntity>  followEntityList = followDao.selectByMap(map);
        List<UserEntity> userEntities = new ArrayList<>(followEntityList.size());
        for (FollowEntity followEntity : followEntityList){
            userEntities.add(userDao.selectById(followEntity.getUserId()));
        }


        List<UserInfo> userInfos = new ArrayList<>(userEntities.size());

        for (UserEntity entity : userEntities){
            UserInfo userInfo = new UserInfo();
            BeanUtils.copyProperties(entity, userInfo);

            userInfos.add(userInfo);
        }

        return userInfos;


    }

    @Override
    public List<UserInfo> getFollows(long userId) {
        Map<String,Object> map = new HashMap<>();
        map.put("user_id", userId);
        List<FollowEntity>  followEntityList = followDao.selectByMap(map);
        List<UserEntity> userEntities = new ArrayList<>(followEntityList.size());
        for (FollowEntity followEntity : followEntityList){
            userEntities.add(userDao.selectById(followEntity.getFollowedId()));
        }

        List<UserInfo> userInfos = new ArrayList<>(userEntities.size());

        for (UserEntity entity : userEntities){
            UserInfo userInfo = new UserInfo();
            BeanUtils.copyProperties(entity, userInfo);

            userInfos.add(userInfo);
        }

        return userInfos;


    }
}
