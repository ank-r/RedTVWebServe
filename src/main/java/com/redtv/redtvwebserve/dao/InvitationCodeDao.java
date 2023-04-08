package com.redtv.redtvwebserve.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.redtv.redtvwebserve.entity.InvitationCodeEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName InvitationCodeDao
 * @Description
 * @Author admin
 * @Time 2023/2/11 12:14
 * @Version 1.0
 */
@Mapper
public interface InvitationCodeDao extends BaseMapper<InvitationCodeEntity> {
}
