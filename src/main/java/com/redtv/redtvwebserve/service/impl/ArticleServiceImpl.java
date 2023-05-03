package com.redtv.redtvwebserve.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.redtv.redtvwebserve.dao.ArticleDao;
import com.redtv.redtvwebserve.dto.VideoArticleDto;
import com.redtv.redtvwebserve.entity.ArticleEntity;
import com.redtv.redtvwebserve.service.ArticleService;
import com.redtv.redtvwebserve.service.UserService;
import com.redtv.redtvwebserve.utils.HostHolder;
import com.redtv.redtvwebserve.vo.ArticleInfo;
import com.redtv.redtvwebserve.vo.UserInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ArticleServiceImpl
 * @Description
 * @Author admin
 * @Time 2023/2/11 15:00
 * @Version 1.0
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleDao articleDao;
    private final UserService userService;


    public ArticleServiceImpl(ArticleDao articleDao, UserService userService   ) {
        this.articleDao = articleDao;
        this.userService = userService;

    }

    @Override
    public int publishVideo(VideoArticleDto videoArticleDto) {
        ArticleEntity articleEntity = new ArticleEntity();
        UserInfo userInfo = HostHolder.getUser();
        System.out.println(userInfo);
        articleEntity.setUserId(userInfo.getId());
        articleEntity.setTitle(videoArticleDto.getTitle());
        articleEntity.setImgUrl(videoArticleDto.getImgUrl());
        articleEntity.setArticleUrl(videoArticleDto.getArticleUrl());
        articleEntity.setDescribes(videoArticleDto.getDescribe());
        articleEntity.setCategory(videoArticleDto.getCategory());
        articleEntity.setCreateTime(System.currentTimeMillis());
        articleEntity.setUpdateTime(System.currentTimeMillis());
       int re =  articleDao.insert(articleEntity);
        return re;
    }

    @Override
    public List<ArticleInfo> getVideoList() {

        QueryWrapper<ArticleEntity> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("create_time");

        List<ArticleEntity>  articleEntities = articleDao.selectList(wrapper);
        List<ArticleInfo> articleInfoList = new ArrayList<>(articleEntities.size());

        for (ArticleEntity articleEntity : articleEntities){
            ArticleInfo articleInfo = new ArticleInfo();
            BeanUtils.copyProperties(articleEntity, articleInfo);

            UserInfo userInfo = userService.getUserById(articleEntity.getUserId());
            articleInfo.setUserInfo(userInfo);

            articleInfoList.add(articleInfo);

        }

        return articleInfoList;
    }

    @Override
    public ArticleInfo getVideoInfoById(long id) {

        ArticleEntity articleEntity = articleDao.selectById(id);

        ArticleInfo articleInfo = new ArticleInfo();
        BeanUtils.copyProperties(articleEntity, articleInfo);

        UserInfo userInfo = userService.getUserById(articleEntity.getUserId());
        articleInfo.setUserInfo(userInfo);
        Long vieCount = articleEntity.getCommentCount() + 1L;
        articleEntity.setViewCount(vieCount);
        //观看数量
        articleDao.updateById(articleEntity);

        return articleInfo;
    }

    @Override
    public List<ArticleInfo> getExamineList() {

        Map<String ,Object>  query = new HashMap<>(2);
        query.put("examine_status",0);
        List<ArticleEntity>  articleEntities = articleDao.selectByMap(query);
        List<ArticleInfo> articleInfoList = new ArrayList<>(articleEntities.size());

        for (ArticleEntity articleEntity : articleEntities){
            ArticleInfo articleInfo = new ArticleInfo();
            BeanUtils.copyProperties(articleEntity, articleInfo);

            UserInfo userInfo = userService.getUserById(articleEntity.getUserId());
            articleInfo.setUserInfo(userInfo);

            articleInfoList.add(articleInfo);

        }

        return articleInfoList;
    }

    @Override
    public List<ArticleInfo> getCategoryList(int categoryId) {

        Map<String ,Object>  query = new HashMap<>(2);
        query.put("examine_status",0);
        query.put("category",categoryId);
        List<ArticleEntity>  articleEntities = articleDao.selectByMap(query);
        List<ArticleInfo> articleInfoList = new ArrayList<>(articleEntities.size());

        for (ArticleEntity articleEntity : articleEntities){
            ArticleInfo articleInfo = new ArticleInfo();
            BeanUtils.copyProperties(articleEntity, articleInfo);

            UserInfo userInfo = userService.getUserById(articleEntity.getUserId());
            articleInfo.setUserInfo(userInfo);

            articleInfoList.add(articleInfo);

        }

        return articleInfoList;
    }

    @Override
    public ArticleEntity getArticleEntityByid(Long id) {
        return articleDao.selectById(id);
    }

    @Override
    public List<ArticleInfo> getFollowVideoList() {




        //列表查询

        List<ArticleEntity>  articleEntities  = null;


        List<ArticleInfo> articleInfoList = new ArrayList<>(articleEntities.size());

        for (ArticleEntity articleEntity : articleEntities){
            ArticleInfo articleInfo = new ArticleInfo();
            BeanUtils.copyProperties(articleEntity, articleInfo);

            UserInfo userInfo = userService.getUserById(articleEntity.getUserId());
            articleInfo.setUserInfo(userInfo);

            articleInfoList.add(articleInfo);

        }

        return articleInfoList;
    }

    @Override
    public List<ArticleInfo> getHotVideoList() {

        QueryWrapper<ArticleEntity> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("score");
        List<ArticleEntity>  articleEntities = articleDao.selectList(wrapper);
        List<ArticleInfo> articleInfoList = new ArrayList<>(articleEntities.size());

        for (ArticleEntity articleEntity : articleEntities){
            ArticleInfo articleInfo = new ArticleInfo();
            BeanUtils.copyProperties(articleEntity, articleInfo);
            UserInfo userInfo = userService.getUserById(articleEntity.getUserId());
            articleInfo.setUserInfo(userInfo);
            articleInfoList.add(articleInfo);
        }
        return articleInfoList;
    }

    @Override
    public List<ArticleInfo> getSearchList(String searchWorld) {

        QueryWrapper<ArticleEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("title",searchWorld);
        List<ArticleEntity>  articleEntities = articleDao.selectList(queryWrapper);
        List<ArticleInfo> articleInfoList = new ArrayList<>(articleEntities.size());

        for (ArticleEntity articleEntity : articleEntities){
            ArticleInfo articleInfo = new ArticleInfo();
            BeanUtils.copyProperties(articleEntity, articleInfo);
            UserInfo userInfo = userService.getUserById(articleEntity.getUserId());
            articleInfo.setUserInfo(userInfo);
            articleInfoList.add(articleInfo);
        }
        return articleInfoList;
    }

    @Override
    public int updateArticle(ArticleEntity articleEntity) {

        return articleDao.updateById(articleEntity);
    }


    private ArticleInfo buildArticleInfo(ArticleEntity articleEntity){

        ArticleInfo articleInfo = new ArticleInfo();
        BeanUtils.copyProperties(articleEntity, articleInfo);

        UserInfo userInfo = userService.getUserById(articleEntity.getUserId());
        articleInfo.setUserInfo(userInfo);
        return articleInfo;
    }




}
