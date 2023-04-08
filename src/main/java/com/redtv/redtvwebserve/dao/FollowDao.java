package com.redtv.redtvwebserve.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.redtv.redtvwebserve.entity.FollowEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName FollowDao
 * @Description
 * @Author admin
 * @Time 2023/2/11 12:14
 * @Version 1.0
 */
@Mapper
public interface FollowDao extends BaseMapper<FollowEntity> {
}
