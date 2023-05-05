package com.redtv.redtvwebserve.service;

import com.redtv.redtvwebserve.dto.PasswordDto;
import com.redtv.redtvwebserve.exception.UpdateInfoException;
import com.redtv.redtvwebserve.vo.UserInfo;

import java.util.List;

/**
 * @ClassName UserService
 * @Description
 * @Author admin
 * @Time 2023/2/21 10:34
 * @Version 1.0
 */
public interface UserService {

    /**
     * get user by id
     * @param id
     * @return
     */
    UserInfo getUserById(long id);

    /**
     * a
     * @return
     */
    List<UserInfo> getUserList();

    int updateInfo(UserInfo userInfo);

    void updatePassword(PasswordDto passwordDto) throws UpdateInfoException;

    List<UserInfo> getFans(long userId);

    List<UserInfo> getFollows(long userId);

}
