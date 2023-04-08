package com.redtv.redtvwebserve.service;

/**
 * @ClassName LikeService
 * @Description
 * @Author admin
 * @Time 2023/2/24 22:55
 * @Version 1.0
 */
public interface LikeService {
    /**
     * 用户是否点赞该视频
     * @param videoId
     * @return   0 没点赞  1点赞
     */
    int isLike(long videoId);

    /**
     * 用户点赞该视频
     * @param videoId
     * @return 0 没点赞  1点赞
     */
    int doLike(long videoId);

    /**
     * 取消点赞该视频
     * @param videoId
     * @return 0 取消点赞失败  1取消点赞成功
     */
    int unLike(long videoId);
}
