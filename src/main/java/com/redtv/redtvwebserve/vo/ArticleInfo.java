package com.redtv.redtvwebserve.vo;

import com.redtv.redtvwebserve.entity.ArticleEntity;
import lombok.Data;

/**
 * @ClassName VideoInfo
 * @Description
 * @Author admin
 * @Time 2023/2/7 11:36
 * @Version 1.0
 */
@Data
public class ArticleInfo extends ArticleEntity {
   private UserInfo userInfo;

   /**
    * 用户是否点赞该视频 0没 1点赞
    */
   private int isLike;

   public static ArticleInfo getTestData(){
      ArticleInfo videoInfo = new ArticleInfo();
      videoInfo.setId(10L);
      videoInfo.setImgUrl("/api/get/file/2023-02-10/wallhaven-0jol1y.jpg");
      videoInfo.setArticleUrl("/api/get/file/2023-02-10/ca9f235b26f4479593036012d7c33a32.MP4");
      videoInfo.setTitle("测试的一天视频");
      videoInfo.setDescribes("测试视频的描述");
      videoInfo.setViewCount(212L);
      videoInfo.setLikeCount(213L);
      videoInfo.setExamineStatus(1);
      videoInfo.setExamineMessage("通过");
      videoInfo.setCategory(1);
      videoInfo.setType(0);
      videoInfo.setUserId(12L);
      videoInfo.setUserInfo(UserInfo.getTest());
      videoInfo.setCreateTime(System.currentTimeMillis());
      videoInfo.setUpdateTime(System.currentTimeMillis());
      videoInfo.setStatus(1);
      videoInfo.setScore(210L);
      videoInfo.setScoreCount(212L);
      videoInfo.setCommentCount(23123L);
      videoInfo.setDanmakuCount(121L);
      videoInfo.setExamineUser(21L);
      videoInfo.setDuration(1122.0);

      return videoInfo;

   }
}
