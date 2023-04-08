package com.redtv.redtvwebserve.dto;

import lombok.Data;

/**
 * @ClassName MessageDto
 * @Description
 * @Author admin
 * @Time 2023/2/22 16:35
 * @Version 1.0
 */
@Data
public class MessageDto {

    /**
     * 发消息的人
     */
    private Long notifier;

    /**
     * 接受消息的人
     */
    private Long receiver;

    /**
     * 接受消息的文章id， 评论对象
     */
    private Long articleId;

    /**
     * 消息内容 , 或者是评论内容
     */
    private String content;

    /**
     * 内容的标题
     */
    private String title;

}
