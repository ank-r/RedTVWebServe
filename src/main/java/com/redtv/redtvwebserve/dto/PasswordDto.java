package com.redtv.redtvwebserve.dto;

import lombok.Data;

/**
 * @ClassName Password
 * @Description  修改密码时候的dto
 * @Author admin
 * @Time 2023/2/22 14:23
 * @Version 1.0
 */
@Data
public class PasswordDto {

   private String oldPassword;

    private String newPassword;

    private String verifyCode;

    private String  captchaToken;
}
