package com.redtv.redtvwebserve.vo;

import com.redtv.redtvwebserve.entity.CommentEntity;
import lombok.Data;

/**
 * @ClassName CommentInfo
 * @Description
 * @Author admin
 * @Time 2023/2/8 12:35
 * @Version 1.0
 */
@Data
public class CommentInfo extends CommentEntity {

    private UserInfo notifierUser;
    public static CommentInfo getTest(){
        CommentInfo commentInfo = new CommentInfo();
        commentInfo.setId(11L);
        commentInfo.setArticleId(123L);
        commentInfo.setUserId(31L);
        commentInfo.setComment("你好，这是测试的评论");
        commentInfo.setStatus(1);
        commentInfo.setCreateTime(System.currentTimeMillis());

        return commentInfo;

    }
}
