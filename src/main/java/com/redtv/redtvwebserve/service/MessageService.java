package com.redtv.redtvwebserve.service;

import com.redtv.redtvwebserve.dto.MessageDto;
import com.redtv.redtvwebserve.exception.MessageException;
import com.redtv.redtvwebserve.vo.MessageInfo;

import java.util.List;

/**
 * @ClassName MessageService
 * @Description
 * @Author admin
 * @Time 2023/2/22 19:56
 * @Version 1.0
 */
public interface MessageService {


    /**
     * 发送一个消息   通过 评论 ， 关注， 点赞 ，私信产生的消息
     * @param messageDto
     * @param type 【0 评论视频， 1 收到关注，2 收到点赞  3 系统通知，4私信】
     * @throws MessageException
     */
    void senMessage(MessageDto messageDto, int type) throws MessageException;

    /**
     * 查询各种类型的消息列表
     * @param type
     * @return
     * @throws MessageException
     */
    List<MessageInfo> getMessageList(int type) throws MessageException;

    /**
     * 读消息
     * @param messagesIds
     */
    void readMessage(List<Long> messagesIds);

    /**
     * 和receiver创建一个会话 ， 有休眠会话，责将其激活
     * @param receiver
     * @return  0创建失败 1创建成功
     */
    int createConversation(Long receiver);

    /**
     * 获取和本用户的所以活跃会话列表
     * @return
     */
    List<MessageInfo> getConversationList();

    /**
     * 获取和 notifier 用户的私信详情列表
     * @param notifier
     * @return
     */
    List<MessageInfo> getConversationDetails(Long notifier);
}
