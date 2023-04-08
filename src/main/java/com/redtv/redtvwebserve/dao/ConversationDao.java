package com.redtv.redtvwebserve.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.redtv.redtvwebserve.entity.ConversationEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName ConversationDao
 * @Description
 * @Author admin
 * @Time 2023/2/25 10:55
 * @Version 1.0
 */
@Mapper
public interface ConversationDao extends BaseMapper<ConversationEntity> {
}
