package com.redtv.redtvwebserve.dto;

import lombok.Data;

/**
 * @ClassName CommentDto
 * @Description
 * @Author admin
 * @Time 2023/2/22 22:27
 * @Version 1.0
 */
@Data
public class CommentDto {
    /**
     * 评论的人
     */
    private Long commentUser;


    /**
     * 接受消息的文章id， 评论对象
     */
    private Long articleId;

    /**
     * 视频文章的作者的ID
     */
    private Long authorId;

    /**
     * 消息内容 , 或者是评论内容
     */
    private String content;

}
