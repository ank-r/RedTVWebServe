package com.redtv.redtvwebserve.service.impl;

import com.redtv.redtvwebserve.dao.CommentDao;
import com.redtv.redtvwebserve.dto.CommentDto;
import com.redtv.redtvwebserve.dto.MessageDto;
import com.redtv.redtvwebserve.entity.CommentEntity;
import com.redtv.redtvwebserve.enums.MessageType;
import com.redtv.redtvwebserve.service.CommentService;
import com.redtv.redtvwebserve.service.MessageService;
import com.redtv.redtvwebserve.service.UserService;
import com.redtv.redtvwebserve.vo.CommentInfo;
import com.redtv.redtvwebserve.vo.UserInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName CommentServiceImpl
 * @Description
 * @Author admin
 * @Time 2023/2/22 22:32
 * @Version 1.0
 */
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentDao commentDao;
    private final MessageService messageService;
    private final UserService userService;

    public CommentServiceImpl(CommentDao commentDao, MessageService messageService, UserService userService) {
        this.commentDao = commentDao;
        this.messageService = messageService;
        this.userService = userService;
    }

    @Override
    public int sendComment(CommentDto commentDto) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setUserId(commentDto.getCommentUser());
        commentEntity.setComment(commentDto.getContent());
        commentEntity.setArticleId(commentDto.getArticleId());
        long now = System.currentTimeMillis();
        commentEntity.setCreateTime(now);
        commentEntity.setUpdateTime(now);
        commentEntity.setStatus(1);
        int re = commentDao.insert(commentEntity);
        if (re>0){
            MessageDto messageDto = new MessageDto();
            messageDto.setContent(commentDto.getContent());
            messageDto.setNotifier(commentDto.getCommentUser());
            messageDto.setReceiver(commentDto.getAuthorId());
            messageDto.setArticleId(commentDto.getArticleId());
            messageService.senMessage(messageDto, MessageType.COMMENT);
        }
        return re;
    }

    @Override
    public List<CommentInfo> getCommentList(long videoId) {
        Map<String,Object> proper = new HashMap<>(2);
        proper.put("article_id", videoId);
        List<CommentEntity> commentEntityList = commentDao.selectByMap(proper);
        List<CommentInfo> commentInfoList = new ArrayList<>(commentEntityList.size());
        for (CommentEntity commentEntity : commentEntityList){
            UserInfo userInfo = userService.getUserById(commentEntity.getUserId());
            CommentInfo commentInfo = new CommentInfo();

            BeanUtils.copyProperties(commentEntity, commentInfo);
            commentInfo.setNotifierUser(userInfo);
            commentInfoList.add(commentInfo);
        }
        return commentInfoList;
    }
}
