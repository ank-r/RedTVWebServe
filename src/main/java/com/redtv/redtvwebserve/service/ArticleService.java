package com.redtv.redtvwebserve.service;

import com.redtv.redtvwebserve.dto.VideoArticleDto;
import com.redtv.redtvwebserve.vo.ArticleInfo;

import java.util.List;

/**
 * @ClassName ArticleService
 * @Description
 * @Author admin
 * @Time 2023/2/11 14:59
 * @Version 1.0
 */
public interface ArticleService {

    /**
     * 上传视频
     * @param videoArticleDto
     * @return
     */
    int publishVideo(VideoArticleDto videoArticleDto);

    /**
     * 获取视频列表
     * @return
     */
    List<ArticleInfo> getVideoList();

    /**
     * 根据文章 id 获得 文章信息
     * @param parseLong
     * @return
     */
    ArticleInfo getVideoInfoById(long parseLong);


    List<ArticleInfo> getExamineList();
}
