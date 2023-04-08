package com.redtv.redtvwebserve.dto;

import lombok.Data;

/**
 * @ClassName LoginDetails
 * @Description
 * @Author admin
 * @Time 2023/2/9 23:14
 * @Version 1.0
 */
@Data
public class LoginDetails {
    private String username;

    private String password;

    private String verifyCode;


    private String mail;

    private String phone;

    private String token;

    /**
     * 管理员的邀请码
     */
    private String invitationCode;
}
