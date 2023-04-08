package com.redtv.redtvwebserve.service;

import com.redtv.redtvwebserve.dto.CommentDto;
import com.redtv.redtvwebserve.vo.CommentInfo;

import java.util.List;

/**
 * @ClassName CommentService
 * @Description
 * @Author admin
 * @Time 2023/2/22 22:31
 * @Version 1.0
 */
public interface CommentService {

    /**
     * 发送一条评论
     * @param commentDto
     * @return
     */
    int sendComment(CommentDto commentDto);

    List<CommentInfo> getCommentList(long videoId);
}
