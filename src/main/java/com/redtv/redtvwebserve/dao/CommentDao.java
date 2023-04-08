package com.redtv.redtvwebserve.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.redtv.redtvwebserve.entity.CommentEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName CommentDao
 * @Description
 * @Author admin
 * @Time 2023/2/11 12:12
 * @Version 1.0
 */
@Mapper
public interface CommentDao extends BaseMapper<CommentEntity> {
}
