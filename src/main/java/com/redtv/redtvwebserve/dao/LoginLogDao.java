package com.redtv.redtvwebserve.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.redtv.redtvwebserve.entity.LoginLogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName LoginLogDao
 * @Description
 * @Author admin
 * @Time 2023/2/11 12:15
 * @Version 1.0
 */
@Mapper
public interface LoginLogDao extends BaseMapper<LoginLogEntity> {
}
