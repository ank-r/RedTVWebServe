package com.redtv.redtvwebserve.schedul;

import com.redtv.redtvwebserve.dao.ArticleDao;
import com.redtv.redtvwebserve.entity.ArticleEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//定时刷新视频的分数
public class VideoScoreRefreshJob  {

    private static final Logger logger = LoggerFactory.getLogger(VideoScoreRefreshJob.class);

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ArticleDao articleDao;



    // 纪元
    private static final Date epoch;

    static {
        try{
            epoch = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2014-08-01 00:00:00");
        }catch (ParseException e){
            throw new RuntimeException("初始客纪元失败!", e);
        }
    }

//    @Override
//    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
//        String redisKey = RedisKeyUtil.getPostScoreKey();
//        BoundSetOperations operations = redisTemplate.boundSetOps(redisKey);
//        redisTemplate.opsForValue().set("aa",11);
//        System.out.println(redisTemplate.opsForValue().get("aa")+"************************");
//        if(operations.size()==0){
////            logger.info("[任务取消] 没有需要刷新的帖子!");
//            return;
//        }
//        logger.info("[任务开始] 正在刷新帖子分数: " + operations.size());
//        while (operations.size()>0) {
//            this.refresh((Integer) operations.pop());
//        }
//        logger.info("[任务结束] 帖子分数刷新完毕!");
//    }

    private void refresh(int videoId) {
        ArticleEntity video = articleDao.selectById(videoId);
        if (video == null) {
            logger.error("该视频不存在: id = " + videoId);
            return;
        }

        // 评论数量
        long commentCount = video.getCommentCount();
        // 点赞数量
        long likeCount = video.getLikeCount();

        // 计算权重
        double w =  + commentCount * 10 + likeCount * 2;
        // 分数 = 帖子权重 + 距离天数
        double score = Math.log10(Math.max(w, 1))
                + (video.getCreateTime() - epoch.getTime()) / (1000 * 3600 * 24);
        // 更新帖子分数
        video.setScore(score);
        articleDao.updateById(video);
    }
}
