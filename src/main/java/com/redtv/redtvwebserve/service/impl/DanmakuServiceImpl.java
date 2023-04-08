package com.redtv.redtvwebserve.service.impl;

import com.redtv.redtvwebserve.dao.DanmakuDao;
import com.redtv.redtvwebserve.dto.DanmakuDto;
import com.redtv.redtvwebserve.entity.DanmakuEntity;
import com.redtv.redtvwebserve.enums.ReturnCodeEnum;
import com.redtv.redtvwebserve.service.DanmakuService;
import com.redtv.redtvwebserve.utils.DanmakuUtils;
import com.redtv.redtvwebserve.vo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName DanmakuServiceImpl
 * @Description
 * @Author admin
 * @Time 2023/2/11 15:43
 * @Version 1.0
 */
@Service
public class DanmakuServiceImpl implements DanmakuService {
    private final DanmakuDao danmakuDao;
    private final RedisTemplate redisTemplate;

    @Autowired
    public DanmakuServiceImpl(DanmakuDao danmakuDao,RedisTemplate redisTemplate) {
        this.danmakuDao = danmakuDao;
        this.redisTemplate = redisTemplate;
    }


    @Override
    public List<Object> danmakuList(Long id, Integer max) {

        Map<String,Object> queryMap = new HashMap<>(4);

        queryMap.put("video_id",id);

        List<DanmakuEntity> danmakuEntityList  =  danmakuDao.selectByMap(queryMap);

        List<Object> danmakuDtos = new LinkedList<>();
        danmakuEntityList.forEach(d->{
            danmakuDtos.add(DanmakuUtils.createDanmaku(
                    d.getTime(),
                    d.getType(),
                    d.getColorDec(),
                    d.getColor(),
                    d.getText()
            ));

        });



        return danmakuDtos;
    }

    @Override
    public ReturnCodeEnum saveDanmaku(DanmakuDto danmakuDto, HttpServletRequest request) {

        DanmakuEntity danmakuEntity = new DanmakuEntity();


        //根据token解析出用户
        String token = danmakuDto.getToken();
        if(token == null || token.length() == 0 || token ==""){
            return ReturnCodeEnum.NO_LOGIN;
        }
        UserInfo userInfo = (UserInfo) redisTemplate.opsForValue().get(token);
        danmakuEntity.setAuthor(userInfo.getId());

        danmakuEntity.setColorDec(danmakuDto.getColor());
        danmakuEntity.setVideoId(danmakuDto.getId());
        danmakuEntity.setText(danmakuDto.getText());
        danmakuEntity.setColor(Long.toHexString(danmakuDto.getColor()));
        danmakuEntity.setTime(danmakuDto.getTime());
        danmakuEntity.setType(danmakuDto.getType());

        danmakuDao.insert(danmakuEntity);

        return ReturnCodeEnum.SUCCESS;
    }
}
