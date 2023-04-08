package com.redtv.redtvwebserve.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.redtv.redtvwebserve.entity.FavoritesTableEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName FavoritesTableDao
 * @Description
 * @Author admin
 * @Time 2023/2/11 12:13
 * @Version 1.0
 */
@Mapper
public interface FavoritesTableDao extends BaseMapper<FavoritesTableEntity> {
}
