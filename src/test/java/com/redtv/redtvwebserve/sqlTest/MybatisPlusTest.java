package com.redtv.redtvwebserve.sqlTest;

import com.redtv.redtvwebserve.dao.*;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @ClassName MybatisPlusTest
 * @Description
 * @Author admin
 * @Time 2023/2/11 14:56
 * @Version 1.0
 */
@SpringBootTest
public class MybatisPlusTest {

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


}
