package com.redtv.redtvwebserve.service;

/**
 * @ClassName FollowService
 * @Description
 * @Author admin
 * @Time 2023/2/24 22:49
 * @Version 1.0
 */
public interface FollowService {

     int checkFollow(long userId) ;

     int doFollow(long userId) ;


     int unFollow(long userId) ;

}
