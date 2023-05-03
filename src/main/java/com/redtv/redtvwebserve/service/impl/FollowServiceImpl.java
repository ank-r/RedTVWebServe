package com.redtv.redtvwebserve.service.impl;

import com.redtv.redtvwebserve.dao.FollowDao;
import com.redtv.redtvwebserve.dto.MessageDto;
import com.redtv.redtvwebserve.entity.FollowEntity;
import com.redtv.redtvwebserve.enums.MessageType;
import com.redtv.redtvwebserve.service.FollowService;
import com.redtv.redtvwebserve.service.MessageService;
import com.redtv.redtvwebserve.utils.HostHolder;
import com.redtv.redtvwebserve.vo.UserInfo;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName FollowServiceImpl
 * @Description
 * @Author admin
 * @Time 2023/2/24 22:50
 * @Version 1.0
 */
@Service
public class FollowServiceImpl implements FollowService {
    private final FollowDao followDao;
    private MessageService messageService;

    public FollowServiceImpl(FollowDao followDao, MessageService messageService) {
        this.followDao = followDao;
        this.messageService = messageService;
    }

    @Override
    public int checkFollow(long userId) {

        UserInfo userInfo = HostHolder.getUser();
        Map<String ,Object > query = new HashMap<>(8);
        query.put("user_id", userInfo.getId());
        query.put("followed_id", userId);
        query.put("status", 1);
        List<FollowEntity> followEntity = followDao.selectByMap(query);

        if (followEntity.size()>=1){
            return 1;
        }

        return 0;
    }

    @Override
    public int doFollow(long userId) {
        int isFollow = this.checkFollow(userId);
        if (isFollow == 1){
            return isFollow;
        }
        Map<String ,Object > query = new HashMap<>(8);
        query.put("user_id", HostHolder.getUser().getId());
        query.put("followed_id", userId);

        List<FollowEntity> followEntities = followDao.selectByMap(query);
        FollowEntity followEntity;
        if (followEntities.size()>0){
            followEntity = followEntities.get(0);
            followEntity.setStatus(1);
            int re = followDao.updateById(followEntity);
            if (re>0){
                return 1;
            }
        }
        followEntity = new FollowEntity();
        followEntity.setUserId(HostHolder.getUser().getId());
        followEntity.setFollowedId(userId);
        followEntity.setEachOther(0);
        followEntity.setStatus(1);
        followEntity.setCreateTime(System.currentTimeMillis());

        int re = followDao.insert(followEntity);
        if (re>0){
            MessageDto messageDto = new MessageDto();
            messageDto.setNotifier(HostHolder.getUser().getId());
            messageDto.setReceiver(userId);
            messageService.senMessage(messageDto, MessageType.FOLLOW);

            return 1;
        }
        return 0;
    }

    @Override
    public int unFollow(long userId) {

        Map<String ,Object > query = new HashMap<>(2);
        query.put("user_id", HostHolder.getUser().getId());
        query.put("followed_id", userId);

        List<FollowEntity> followEntities = followDao.selectByMap(query);
        FollowEntity followEntity;
        if (followEntities.size()>0){
            followEntity = followEntities.get(0);
            followEntity.setStatus(0);
            int re = followDao.updateById(followEntity);
            if (re>0){
                return 0;
            }

        }

        return 1;
    }
}
