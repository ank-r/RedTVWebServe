package com.redtv.redtvwebserve.enums;

/**
 * @ClassName MessageType
 * @Description
 * @Author admin
 * @Time 2023/2/22 20:03
 * @Version 1.0
 */
public class MessageType {
    //类型 【0 评论视频， 1 收到关注，2 收到点赞  3 系统通知，4私信】
    /**
     * 评论
     */
    public static final  int COMMENT = 0;
    /**
     * 关注
     */
    public static final  int FOLLOW = 1;
    /**
     * 点赞
     */
    public static final  int LIKE = 2;
    /**
     * 系统通知
     */
    public static final  int NOTIFICATION = 3;
    /**
     * 私信
     */
    public static final  int MESSAGE = 4;
}
