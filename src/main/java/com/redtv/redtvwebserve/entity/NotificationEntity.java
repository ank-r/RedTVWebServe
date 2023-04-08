package com.redtv.redtvwebserve.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
 * 消息通知表
 * @author admin
 */
@Data
@TableName("notification")
public class NotificationEntity {
	@TableId(type = IdType.AUTO)
	private Long id;

	/**
	 * 通知发送人ID
	 */
	private Long notifier;

	/**
	 * 通知接收人ID
	 */
	private Long receiver;

	/**
	 * 外部ID，如主帖子ID
	 */
	private Long outerId;

	/**
	 * 评论内容
	 */
	private String content;



	/**
	 * 通知的标题
	 */
	private String notificationTitle;

	/**
	 * 评论目标ID
	 */
	private Long commentId;

	/**
	 * 类型 【0 评论视频， 1 收到关注，2 收到点赞  3 系统通知，4私信】
	 */
	private Integer type;

	/**
	 * 
	 */
	private Long createTime;

	/**
	 * 【0 未读， 1 已读】
	 */
	private Integer status;

}
