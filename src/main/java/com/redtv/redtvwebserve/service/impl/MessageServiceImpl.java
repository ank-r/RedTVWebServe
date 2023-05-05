package com.redtv.redtvwebserve.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.redtv.redtvwebserve.dao.ConversationDao;
import com.redtv.redtvwebserve.dao.NotificationDao;
import com.redtv.redtvwebserve.dto.MessageDto;
import com.redtv.redtvwebserve.entity.ConversationEntity;
import com.redtv.redtvwebserve.entity.NotificationEntity;
import com.redtv.redtvwebserve.enums.MessageType;
import com.redtv.redtvwebserve.exception.MessageException;
import com.redtv.redtvwebserve.service.ArticleService;
import com.redtv.redtvwebserve.service.MessageService;
import com.redtv.redtvwebserve.service.UserService;
import com.redtv.redtvwebserve.utils.HostHolder;
import com.redtv.redtvwebserve.vo.MessageInfo;
import com.redtv.redtvwebserve.vo.UserInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @ClassName MessageServiceImpl
 * @Description
 * @Author admin
 * @Time 2023/2/22 20:19
 * @Version 1.0
 */
@Service
public class MessageServiceImpl implements MessageService {

    private final NotificationDao notificationDao;
    private final UserService userService;
    private final ArticleService articleService;
    private final ConversationDao conversationDao;

    public MessageServiceImpl(NotificationDao notificationDao, @Lazy UserService userService, ArticleService articleService, ConversationDao conversationDao) {
        this.notificationDao = notificationDao;
        this.userService = userService;
        this.articleService = articleService;
        this.conversationDao = conversationDao;
    }

    @Override
    public void senMessage(MessageDto messageDto, int type) throws MessageException {

        NotificationEntity notificationEntity = new NotificationEntity();
        notificationEntity.setCreateTime(System.currentTimeMillis());
        notificationEntity.setStatus(0);
        notificationEntity.setType(type);
        notificationEntity.setNotifier(messageDto.getNotifier());
        notificationEntity.setReceiver(messageDto.getReceiver());
        notificationEntity.setContent(messageDto.getContent());
        //评论
        if (type == MessageType.COMMENT){
            // 评论的视频id
            notificationEntity.setCommentId(messageDto.getArticleId());
        }
        //点赞
        if (type == MessageType.LIKE){
            //点赞设置外部ID
            notificationEntity.setOuterId(messageDto.getArticleId());
        }
        if (type == MessageType.MESSAGE){
            createConversation(messageDto.getReceiver());
        }

        int re = notificationDao.insert(notificationEntity);
        if (re<= 0){
            throw  new MessageException("系统出错，请重试");
        }

    }

    @Override
    public List<MessageInfo> getMessageList(int type) throws MessageException {
        UserInfo userInfo = HostHolder.getUser();

        Map<String , Object> select = new HashMap<>(4);
        select.put("receiver", userInfo.getId());
        select.put("status", 0);
        select.put("type", type);

        List<NotificationEntity> notificationEntities = notificationDao.selectByMap(select);

        List<MessageInfo> messageInfos = new ArrayList<>(notificationEntities.size());
        for (NotificationEntity notificationEntity: notificationEntities){
            MessageInfo messageInfo = new MessageInfo();
            BeanUtils.copyProperties(notificationEntity, messageInfo);
            messageInfo.setNotifierUser(userService.getUserById(messageInfo.getNotifier()));

            if (type == MessageType.LIKE){
                messageInfo.setArticleInfo(articleService.getVideoInfoById(notificationEntity.getOuterId()));
            }

            if (type == MessageType.COMMENT){
                messageInfo.setArticleInfo(articleService.getVideoInfoById(notificationEntity.getCommentId()));

            }
            messageInfos.add(messageInfo);
        }

        return messageInfos;
    }

    @Override
    public void readMessage(List<Long> messagesIds) {

        List<NotificationEntity> notificationEntities = notificationDao.selectBatchIds(messagesIds);
        for (NotificationEntity notification:notificationEntities){
            notification.setStatus(1);
            notificationDao.updateById(notification);
        }

    }

    @Override
    public int createConversation(Long receiver) {

        Long userId = HostHolder.getUser().getId();
        String talkId1 = userId+""+receiver;
        String talkId2 = receiver+""+userId;
        ConversationEntity conversationEntity1 = conversationDao.selectById(talkId1);
        ConversationEntity conversationEntity2 = conversationDao.selectById(talkId2);
        if(conversationEntity1 != null && conversationEntity1.getStatus() ==1){
            return 1;
        }

        if (conversationEntity1 != null && conversationEntity1.getStatus() !=1){
            //激活自己作为发送方的会话
            conversationEntity1.setStatus(1);
            conversationDao.updateById(conversationEntity1);

            //激活对方的
            conversationEntity2.setStatus(1);
            conversationDao.updateById(conversationEntity2);


        }else {

            long now = System.currentTimeMillis();

            //自己这方
            conversationEntity1 = new ConversationEntity();
            conversationEntity1.setTalkId(talkId1);
            conversationEntity1.setStatus(1);
            conversationEntity1.setNotifier(userId);
            conversationEntity1.setReceiver(receiver);
            conversationEntity1.setCreateTime(now);
            conversationDao.insert(conversationEntity1);

            //对方
            conversationEntity2 = new ConversationEntity();
            conversationEntity1.setTalkId(talkId2);
            conversationEntity2.setStatus(1);
            conversationEntity2.setNotifier(receiver);
            conversationEntity2.setReceiver(userId);
            conversationEntity2.setCreateTime(now);
            conversationDao.insert(conversationEntity2);

        }

        return 1;
    }

    @Override
    public List<MessageInfo> getConversationList() {

        Map<String ,Object> select  =new HashMap<>(4);
        //发给我的活跃的会话
        select.put("receiver", HostHolder.getUser().getId());
        select.put("status", 1);

        List<ConversationEntity> conversationEntityList = conversationDao.selectByMap(select);
        List<MessageInfo> messageInfoList = new ArrayList<>(conversationEntityList.size());
        for (ConversationEntity conversation : conversationEntityList){

            QueryWrapper<NotificationEntity> wrapper = new QueryWrapper<>();
            select.put("notifier",conversation.getNotifier());
            // 0 未读
            select.put("status", 0);
            wrapper.allEq(select);
            wrapper.orderBy(true,false,"create_time" );
            // 有SQL注入的风险
            wrapper.last("limit 1");

            NotificationEntity notification = notificationDao.selectOne(wrapper);

            MessageInfo messageInfo = new MessageInfo();
            BeanUtils.copyProperties(notification,messageInfo);
            UserInfo notifier = userService.getUserById(messageInfo.getNotifier());
            messageInfo.setNotifierUser(notifier);

            messageInfoList.add(messageInfo);
        }

        return messageInfoList;
    }

    @Override
    public List<MessageInfo> getConversationDetails(Long notifier) {

        long userId = HostHolder.getUser().getId();
        Map<String , Object> select = new HashMap<>();
        select.put("notifier", notifier);
        select.put("receiver", userId);
        select.put("status", 0);

        List<NotificationEntity> notificationEntities = notificationDao.selectByMap(select);

        select.put("notifier", userId);
        select.put("receiver", notifier);

        notificationEntities.addAll(notificationDao.selectByMap(select));

        notificationEntities.sort((t1, t2) -> (int) (t1.getCreateTime()- t2.getCreateTime()));

        List<MessageInfo> messageInfos = new ArrayList<>(notificationEntities.size());
        for (NotificationEntity notification:notificationEntities ){
            MessageInfo messageInfo = new MessageInfo();
            BeanUtils.copyProperties(notification, messageInfo);
            messageInfo.setNotifierUser(userService.getUserById(messageInfo.getNotifier()));

            messageInfos.add(messageInfo);
        }



        return messageInfos;
    }


}
