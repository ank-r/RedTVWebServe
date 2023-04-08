package com.redtv.redtvwebserve.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.redtv.redtvwebserve.entity.CategoryEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName CategoryDao
 * @Description
 * @Author admin
 * @Time 2023/2/11 12:11
 * @Version 1.0
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
}
