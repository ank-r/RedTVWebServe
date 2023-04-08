package com.redtv.redtvwebserve.vo;

import com.redtv.redtvwebserve.entity.NotificationEntity;
import lombok.Data;

/**
 * @ClassName MessageInfo
 * @Description
 * @Author admin
 * @Time 2023/2/8 12:35
 * @Version 1.0
 */
@Data
public class MessageInfo extends NotificationEntity {
    /**
     * 发消息的人
     */
    private UserInfo notifierUser;

    /**
     * 接受消息的人
     */
    private UserInfo receiverUser;

    /**
     * 消息相关的 目标 文章
     */
    private ArticleInfo articleInfo;


    public static MessageInfo getTest(){
        MessageInfo messageInfo = new MessageInfo();
        messageInfo.setId(12L);
        messageInfo.setNotifier(22L);
        messageInfo.setReceiver(33L);
        messageInfo.setOuterId(20L);
        messageInfo.setContent("随便的内容");
        messageInfo.setCommentId(20L);
        messageInfo.setType(0);
        messageInfo.setStatus(0);
        messageInfo.setCreateTime(System.currentTimeMillis());
        return messageInfo;
    }
}
