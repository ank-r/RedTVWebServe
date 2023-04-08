package com.redtv.redtvwebserve.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @ClassName ConversationEntity
 * @Description
 * @Author admin
 * @Time 2023/2/25 10:50
 * @Version 1.0
 */
@Data
@TableName("conversation")
public class ConversationEntity {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private String talkId;

    /**
     *
     */
    private Long notifier;

    /**
     *
     */
    private Long receiver;

    /**
     *
     */
    private Long createTime;


    private int status;

}
