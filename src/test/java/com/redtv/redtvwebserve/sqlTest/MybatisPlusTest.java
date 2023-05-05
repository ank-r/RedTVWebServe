package com.redtv.redtvwebserve.sqlTest;

import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.redtv.redtvwebserve.dao.*;
import com.redtv.redtvwebserve.entity.ArticleEntity;
import com.redtv.redtvwebserve.entity.FollowEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @ClassName MybatisPlusTest
 * @Description
 * @Author admin
 * @Time 2023/2/11 14:56
 * @Version 1.0
 */
@SpringBootTest
public class MybatisPlusTest {

    @Autowired
    private ArticleDao articleDao;

    private CategoryDao categoryDao;

    private CommentDao commentDao;

    private DanmakuDao danmakuDao;

    private FavoritesTableDao  favoritesTableDao;

    private FollowDao followDao;

    private InvitationCodeDao invitationCodeDao;

    private LoginLogDao loginLogDao;

    private NotificationDao notificationDao;

    private UserDao userDao;

    @Test
    public void test1(){


    }


    @Test
    void testJoin() {
        List<ArticleEntity> list = articleDao.selectJoinList(ArticleEntity.class,
                new MPJLambdaWrapper<ArticleEntity>()
                        .selectAll(ArticleEntity.class)
                        .leftJoin(FollowEntity.class, FollowEntity::getFollowedId, ArticleEntity::getUserId)
                        .eq(FollowEntity::getUserId, 6));
        System.out.println(list.size());

    }


}
