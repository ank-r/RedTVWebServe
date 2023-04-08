package com.redtv.redtvwebserve.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.redtv.redtvwebserve.entity.ArticleEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName ArticleDao
 * @Description
 * @Author admin
 * @Time 2023/2/11 12:08
 * @Version 1.0
 */
@Mapper
public interface ArticleDao extends BaseMapper<ArticleEntity> {

}
