package com.redtv.redtvwebserve.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.redtv.redtvwebserve.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName UserDao
 * @Description
 * @Author admin
 * @Time 2023/2/11 12:16
 * @Version 1.0
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {

}
