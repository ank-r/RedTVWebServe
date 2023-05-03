package com.redtv.redtvwebserve.service;

import com.redtv.redtvwebserve.dto.LoginDetails;
import com.redtv.redtvwebserve.vo.UserInfo;

import javax.security.auth.login.LoginException;

/**
 * @ClassName UserService
 * @Description
 * @Author admin
 * @Time 2023/2/15 21:31
 * @Version 1.0
 */
public interface LogInService {


    /**
     * 用户注册
     * @param loginDetails
     * @throws LoginException
     */
    void userRegister(LoginDetails loginDetails) throws LoginException;

    /**
     * 用户登录
     * @param loginDetails
     * @return
     * @throws LoginException
     */
    UserInfo userLogin(LoginDetails loginDetails) throws  LoginException ;

    UserInfo adminLogin(LoginDetails loginDetails) throws  LoginException ;


}
