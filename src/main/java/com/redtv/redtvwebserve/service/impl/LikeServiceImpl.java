package com.redtv.redtvwebserve.service.impl;

import com.redtv.redtvwebserve.dao.FavoritesTableDao;
import com.redtv.redtvwebserve.dto.MessageDto;
import com.redtv.redtvwebserve.entity.FavoritesTableEntity;
import com.redtv.redtvwebserve.enums.MessageType;
import com.redtv.redtvwebserve.service.ArticleService;
import com.redtv.redtvwebserve.service.LikeService;
import com.redtv.redtvwebserve.service.MessageService;
import com.redtv.redtvwebserve.utils.HostHolder;
import com.redtv.redtvwebserve.vo.ArticleInfo;
import com.redtv.redtvwebserve.vo.UserInfo;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName LikeServiceImpl
 * @Description
 * @Author admin
 * @Time 2023/2/24 22:55
 * @Version 1.0
 */
@Service
public class LikeServiceImpl implements LikeService {
    private final FavoritesTableDao favoritesTableDao;
    private final MessageService messageService;
    private final ArticleService articleService;

    public LikeServiceImpl(FavoritesTableDao favoritesTableDao, MessageService messageService, ArticleService articleService) {
        this.favoritesTableDao = favoritesTableDao;
        this.messageService = messageService;
        this.articleService = articleService;
    }

    @Override
    public int isLike(long videoId) {

        UserInfo userInfo = HostHolder.getUser();

        Map<String ,Object> proper = new HashMap<>(2);
        proper.put("article_id", videoId);
        proper.put("user_id", userInfo.getId());
        List<FavoritesTableEntity> favoritesTableEntities = favoritesTableDao.selectByMap(proper);

        if (favoritesTableEntities.size()>0){
            return 1;
        }
        return 0;
    }

    @Override
    public int doLike(long videoId) {

        FavoritesTableEntity favoritesTableEntity = new FavoritesTableEntity();
        favoritesTableEntity.setArticleId(videoId);
        favoritesTableEntity.setUserId(HostHolder.getUser().getId());
        favoritesTableEntity.setCreateTime(System.currentTimeMillis());

        int re = favoritesTableDao.insert(favoritesTableEntity);
        if (re>0){

            //发个消息
            ArticleInfo articleInfo = articleService.getVideoInfoById(videoId);

            MessageDto messageDto = new MessageDto();
            messageDto.setNotifier(HostHolder.getUser().getId());
            messageDto.setReceiver(articleInfo.getUserId());
            messageDto.setArticleId(videoId);

            messageService.senMessage(messageDto, MessageType.LIKE);

            return 1;
        }

        return 0;
    }

    @Override
    public int unLike(long videoId) {

        Map<String ,Object>  proper = new HashMap<>(2);
        proper.put("article_id", videoId);
        proper.put("user_id", HostHolder.getUser().getId());
        List<FavoritesTableEntity> favoritesTableEntities = favoritesTableDao.selectByMap(proper);

        if (favoritesTableEntities.size()>0){
            FavoritesTableEntity favoritesTableEntity = favoritesTableEntities.get(0);
            int re = favoritesTableDao.deleteById(favoritesTableEntity.getId());
            if (re>0){
                //取消点赞成功
                return 1;
            }
            return 0;
        }
        return 0;
    }
}
