package com.redtv.redtvwebserve.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
 * 关注列表
 * @author admin
 */
@Data
@TableName("follow")
public class FollowEntity {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Long id;



    /**
     *
     */
    private Long userId;


    /**
     *
     */
    private Long followedId;

    /**
     * 是否互相关注 0 没有 1 互相关注
     * [0 正常  1 删除]
     */
    private Integer eachOther;

    /**
     * [0 正常  1 删除]
     */
    private Integer status;

    /**
     *
     */
    private Long createTime;

}
