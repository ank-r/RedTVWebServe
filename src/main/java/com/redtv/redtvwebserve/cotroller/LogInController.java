package com.redtv.redtvwebserve.cotroller;

import com.redtv.redtvwebserve.dto.LoginDetails;
import com.redtv.redtvwebserve.service.LogInService;
import com.redtv.redtvwebserve.vo.ResponseDetails;
import com.redtv.redtvwebserve.vo.UserInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.login.LoginException;

/**
 * @ClassName LogInController
 * @Description
 * @Author admin
 * @Time 2023/2/9 23:15
 * @Version 1.0
 */
@RestController
public class LogInController {

    private final LogInService logInService;

    public LogInController(LogInService logInService) {
        this.logInService = logInService;
    }

    /**
     * 普通用户登录
     * @return
     */
    @PostMapping("/login/ordinary")
    public ResponseDetails ordinaryLogIn(@RequestBody LoginDetails loginDetails){
        System.out.println("收到用户数据"+loginDetails.toString());

        try {

            UserInfo  userInfo =  logInService.userLogin(loginDetails);

            return ResponseDetails.ok().data(userInfo);

        }catch (LoginException loginException){

            return ResponseDetails.error(loginException.getMessage());
        }




    }

    /**
     * 用户注册
     * @return
     */
    @PostMapping("/user/register")
    public ResponseDetails register(@RequestBody LoginDetails loginDetails){
        System.out.println("收到用户数据"+loginDetails.toString());

        try {
            logInService.userRegister(loginDetails);
        }catch (LoginException loginException){

            return ResponseDetails.error(loginException.getMessage());
        }

        return ResponseDetails.ok("注册成功");
    }

}
