package com.redtv.redtvwebserve.cotroller;

import com.redtv.redtvwebserve.dto.PasswordDto;
import com.redtv.redtvwebserve.enums.ReturnCodeEnum;
import com.redtv.redtvwebserve.exception.UpdateInfoException;
import com.redtv.redtvwebserve.service.FollowService;
import com.redtv.redtvwebserve.service.UserService;
import com.redtv.redtvwebserve.vo.ResponseDetails;
import com.redtv.redtvwebserve.vo.UserInfo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName UserController
 * @Description
 * @Author admin
 * @Time 2023/2/8 11:48
 * @Version 1.0
 */
@RestController
public class UserController {

    private final UserService userService;
    private final FollowService followService;

    public UserController(UserService userService, FollowService followService) {
        this.userService = userService;
        this.followService = followService;
    }

    @GetMapping("/user/getUserInfo/{id}")
    public ResponseDetails getUserInfoById(@PathVariable String id){
        System.out.println("收到一个用户id"+id);
        UserInfo userInfo = userService.getUserById(Long.parseLong(id));

        return ResponseDetails.ok("success").data(userInfo);
    }

    /**
     * 判断登录用户是否关注该id用户
     * @param id
     * @return
     */
    @GetMapping("/user/isFollow/{id}")
    public ResponseDetails checkFollow(@PathVariable String id){

        long userId = Long.parseLong(id);
        int isFollow =  followService.checkFollow(userId);

        return ResponseDetails.ok("success").put("isFollow", isFollow);
    }

    @GetMapping("/user/doFollow/{id}")
    public ResponseDetails doFollow(@PathVariable String id){

        long userId = Long.parseLong(id);
        int isFollow =  followService.doFollow(userId);

        return ResponseDetails.ok("success").put("isFollow", isFollow);
    }

    @GetMapping("/user/unFollow/{id}")
    public ResponseDetails unFollow(@PathVariable String id){

        long userId = Long.parseLong(id);
        int isFollow =  followService.unFollow(userId);

        return ResponseDetails.ok("success").put("isFollow", isFollow);
    }


    @GetMapping("/user/get/list")
    public ResponseDetails getUserList(){

        List<UserInfo>  userInfoList = userService.getUserList();

        return ResponseDetails.ok("success").data(userInfoList);
    }



    @PostMapping("/user/update/info")
    public  ResponseDetails updateUserInfo(@RequestBody UserInfo userInfo){
        System.out.println("收到数据");
        System.out.println(userInfo.toString());
        int code = userService.updateInfo(userInfo);
        if (code != ReturnCodeEnum.SUCCESS.getCode()){
            return ResponseDetails.error("更新失败");
        }
        return ResponseDetails.ok("OK");
    }

    @PostMapping("/user/update/password")
    public  ResponseDetails updateUserPassWorld(@RequestBody PasswordDto passwordDto){

        System.out.println(passwordDto.toString());
         try {
             userService.updatePassword(passwordDto);
         }catch (UpdateInfoException updateInfoException){
             return ResponseDetails.error(updateInfoException.getMessage());
         }catch (Exception e){
             return ResponseDetails.ok("系统出错！修改失败");
         }

        return ResponseDetails.ok("更新密码成功");
    }



}
